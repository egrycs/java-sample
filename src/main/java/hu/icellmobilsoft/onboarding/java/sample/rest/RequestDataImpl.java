package hu.icellmobilsoft.onboarding.java.sample.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceDataTypeConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.LineConverter;

public class RequestDataImpl implements IRequestData {

    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    private InvoiceConverter invoiceConverter;
    private LineConverter lineConverter;
    private InvoiceDataTypeConverter invoiceDataTypeConverter;

    public RequestDataImpl(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineRepository = lineRepository;
        invoiceConverter = new InvoiceConverter();
        lineConverter = new LineConverter();
        invoiceDataTypeConverter = new InvoiceDataTypeConverter();
    }

    public LineType getLine(String id) throws BaseException {
        Optional<Line> optionalLine = lineRepository.findLine(id);
        if (optionalLine.isEmpty()) {
            throw new BaseException("Entity not found!");
        }

        return lineConverter.convert(optionalLine.get());
    }

    public LineListType getAllLine() {
        LineListType lineListType = new LineListType();
        lineRepository.getAllLines().forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));

        return lineListType;
    }

    public LineListType queryLine(LineListQueryType lineListQuery) throws BaseException {
        if (lineListQuery == null) {
            throw new BaseException("Filter not provided!");
        }
        LineListType lineListType = new LineListType();
        boolean hasName = lineListQuery.getName() != null;
        boolean hasUnitOfMeasure = lineListQuery.getUnitOfMeasure() != null;

        if (hasName || hasUnitOfMeasure) {
            setLineListType(
                    lineRepository.getAllLines()
                            .stream()
                            .filter(
                                    line -> hasName && line.getName().contains(lineListQuery.getName())
                                            || hasUnitOfMeasure && line.getUnitOfMeasure().equals(lineListQuery.getUnitOfMeasure().value()))
                            .toList(),
                    lineListType);
        } else {
            setLineListType(lineRepository.getAllLines(), lineListType);
        }

        return lineListType;
    }

    public LineType saveLine(LineType line) {
        return lineConverter.convert(lineRepository.saveLine(lineConverter.convert(line)));
    }

    public LineType deleteLine(String id) throws BaseException {
        if (isLineAssignedToInvoice(id)) {
            throw new BaseException("The line with id " + id + " is assigned to an invoice and cannot be deleted.");
        }
        Line deletedLine = lineRepository.deleteLine(id);

        return lineConverter.convert(deletedLine);
    }

    public InvoiceDataType getInvoiceData(String id) throws BaseException {
        Invoice invoice = invoiceRepository.getInvoice(id);

        return invoiceDataTypeConverter.convert(invoice, getInvoiceLines(invoice));
    }

    public InvoiceDataListType getAllInvoiceData() {
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();
        setInvoiceDataListType(invoiceRepository.getAllInvoices(), invoiceDataListType);

        return invoiceDataListType;
    }

    public InvoiceDataListType queryInvoicesData(InvoiceDataListQueryType invoiceListQuery) throws BaseException {
        if (invoiceListQuery == null) {
            throw new BaseException("Filter not provided!");
        }
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();
        boolean hasInvoiceNumber = invoiceListQuery.getInvoiceNumber() != null;
        boolean hasInvoiceType = invoiceListQuery.getInvoiceType() != null;

        if (hasInvoiceNumber || hasInvoiceType) {
            setInvoiceDataListType(
                    invoiceRepository.getAllInvoices()
                            .stream()
                            .filter(
                                    invoice -> hasInvoiceNumber && invoice.getInvoiceNumber().contains(invoiceListQuery.getInvoiceNumber())
                                            || hasInvoiceType && invoice.getInvoiceType().equals(invoiceListQuery.getInvoiceType()))
                            .toList(),
                    invoiceDataListType);
        } else {
            setInvoiceDataListType(invoiceRepository.getAllInvoices(), invoiceDataListType);
        }

        return invoiceDataListType;
    }

    public InvoiceDataType saveInvoiceData(InvoiceDataType invoiceData) {
        Invoice invoice = invoiceConverter.convert(invoiceData.getInvoice());
        List<String> lineIds = new ArrayList<>();
        invoiceData.getLines().getLine().forEach(lineType -> lineIds.add(lineRepository.saveLine(lineConverter.convert(lineType)).getId()));
        invoice.setLines(lineIds);
        Invoice savedInvoice = invoiceRepository.saveInvoice(invoice);
        List<Line> savedLines = lineRepository.getAllLines().stream().filter(line -> invoice.getLines().contains(line.getId())).toList();

        return invoiceDataTypeConverter.convert(savedInvoice, savedLines);
    }

    public InvoiceDataType modifyInvoiceData(InvoiceDataType invoiceData) {
        Invoice invoice = invoiceConverter.convert(invoiceData.getInvoice());
        Invoice savedInvoice = invoiceRepository.saveInvoice(invoice);
        List<Line> savedLines = lineRepository.getAllLines().stream().filter(line -> savedInvoice.getLines().contains(line.getId())).toList();

        return invoiceDataTypeConverter.convert(savedInvoice, savedLines);
    }

    public InvoiceDataType deleteInvoice(String id) throws BaseException {
        Invoice deletedInvoice = invoiceRepository.getInvoice(id);
        List<Line> deletedInvoiceLines = lineRepository.getAllLines()
                .stream()
                .filter(line -> deletedInvoice.getLines().contains(line.getId()))
                .toList();
        invoiceRepository.deleteInvoice(id);

        return invoiceDataTypeConverter.convert(deletedInvoice, deletedInvoiceLines);
    }

    private void setLineListType(List<Line> lines, LineListType lineListType) {
        lines.forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));
    }

    private List<Line> getInvoiceLines(Invoice invoice) {
        return invoice.getLines() == null ? Collections.emptyList() : lineRepository.getLines(invoice.getLines());
    }

    private void setInvoiceDataListType(List<Invoice> invoices, InvoiceDataListType invoiceDataListType) {
        invoices.forEach(invoice -> invoiceDataListType.getInvoiceData().add(invoiceDataTypeConverter.convert(invoice, getInvoiceLines(invoice))));
    }

    private boolean isLineAssignedToInvoice(String lineId) {
        return invoiceRepository.getAllInvoices()
                .stream()
                .flatMap(invoice -> invoice.getLines() == null ? Stream.of() : invoice.getLines().stream())
                .anyMatch(line -> line.equals(lineId));
    }
}
