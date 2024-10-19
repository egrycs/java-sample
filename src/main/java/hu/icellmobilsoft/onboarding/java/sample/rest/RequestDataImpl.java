package hu.icellmobilsoft.onboarding.java.sample.rest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineType;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceDataTypeConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.LineConverter;

public class RequestDataImpl implements IRequestData {

    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    private LineConverter lineConverter;
    private InvoiceDataTypeConverter invoiceDataTypeConverter;

    public RequestDataImpl(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineRepository = lineRepository;
        lineConverter = new LineConverter();
        invoiceDataTypeConverter = new InvoiceDataTypeConverter();
    }

    public InvoiceDataType getInvoiceData(String id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findInvoice(id);

        return optionalInvoice.map(invoice -> invoiceDataTypeConverter.convert(invoice, getInvoiceLines(invoice))).orElse(null);
    }

    public InvoiceDataListType getAllInvoicesData() {
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();
        setInvoiceDataListType(invoiceRepository.getAllInvoices(), invoiceDataListType);

        return invoiceDataListType;
    }

    public InvoiceDataListType queryInvoicesData(String invoiceNumber, String invoiceType) {
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();

        setInvoiceDataListType(
                invoiceRepository.getAllInvoices()
                        .stream()
                        .filter(invoice -> invoice.getInvoiceNumber().equals(invoiceNumber) || invoice.getInvoiceType().equals(invoiceType))
                        .toList(),
                invoiceDataListType);

        return invoiceDataListType;
    }

    public LineType getLine(String id) throws BaseException {
        Optional<Line> line = lineRepository.findLine(id);
        if (line.isEmpty()) {
            throw new BaseException("Entity with id " + id + " not found.");
        }

        return lineConverter.convert(line.get());
    }

    public LineListType getAllLine() {
        LineListType lineListType = new LineListType();
        lineRepository.getAllLines().forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));

        return lineListType;
    }

    public LineType saveLine(LineType line) {
        return lineConverter.convert(lineRepository.saveLine(lineConverter.convert(line)));
    }

    public LineType deleteLine(String id) throws BaseException {
        if (isLineAssignedToInvoice(id)) {
            throw new BaseException("The line with id " + id + " is assigned to an invoice and cannot be deleted.");
        }
        return lineConverter.convert(lineRepository.deleteLine(id));
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
