package hu.icellmobilsoft.onboarding.java.sample.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.converter.InvoiceConverter;
import hu.icellmobilsoft.onboarding.java.sample.converter.InvoiceDataTypeConverter;
import hu.icellmobilsoft.onboarding.java.sample.converter.LineConverter;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;

@Model
public class RequestDataImpl implements IRequestData {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private LineService lineService;

    private InvoiceConverter invoiceConverter = new InvoiceConverter();
    private LineConverter lineConverter = new LineConverter();
    private InvoiceDataTypeConverter invoiceDataTypeConverter = new InvoiceDataTypeConverter();

    public LineType getLine(String id) throws BaseException {
        Line line = lineService.findLine(id);
        if (line == null) {
            throw new BaseException("Entity not found!");
        }

        return lineConverter.convert(line);
    }

    public LineListType getAllLine() {
        LineListType lineListType = new LineListType();
        lineService.getAllLines(new LineListQueryType(), new LineListQueryOrderType(), new QueryRequestDetails())
                .forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));

        return lineListType;
    }

    public LineListType queryLine(LineListQueryType queryParams, LineListQueryOrderType orderParams, QueryRequestDetails paginationParams)
            throws BaseException {
        if (queryParams == null) {
            throw new BaseException("Filter not provided!");
        }
        LineListType lineListType = new LineListType();
        setLineListType(lineService.getAllLines(queryParams, orderParams, paginationParams), lineListType);

        return lineListType;
    }

    public LineType saveLine(LineType line) {
        return lineConverter.convert(lineService.saveLine(lineConverter.convert(line)));
    }

    public LineType deleteLine(String id) throws BaseException {
        if (isLineAssignedToInvoice(id)) {
            throw new BaseException("The line with id " + id + " is assigned to an invoice and cannot be deleted.");
        }
        Line deletedLine = lineService.deleteLine(id);

        return lineConverter.convert(deletedLine);
    }

    public InvoiceDataType getInvoiceData(String id) throws BaseException {
        Invoice invoice = invoiceService.getInvoice(id);

        return invoiceDataTypeConverter.convert(invoice, getInvoiceLines(invoice));
    }

    public boolean isInvoiceDataTableEmpty() {
        return invoiceService.isInvoiceDataTableEmpty();
    }

    public InvoiceDataListType getAllInvoiceData() {
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();
        setInvoiceDataListType(
                invoiceService.getAllInvoices(new InvoiceDataListQueryType(), new InvoiceDataListQueryOrderType(), new QueryRequestDetails()),
                invoiceDataListType);

        return invoiceDataListType;
    }

    public InvoiceDataListType queryInvoicesData(InvoiceDataListQueryType queryParams, InvoiceDataListQueryOrderType orderParams,
            QueryRequestDetails paginationParams) throws BaseException {
        if (queryParams == null) {
            throw new BaseException("Filter not provided!");
        }
        InvoiceDataListType invoiceDataListType = new InvoiceDataListType();
        setInvoiceDataListType(invoiceService.getAllInvoices(queryParams, orderParams, paginationParams), invoiceDataListType);

        return invoiceDataListType;
    }

    @Transactional
    public InvoiceDataType saveInvoiceData(InvoiceDataType invoiceData) {
        Invoice invoice = invoiceConverter.convert(invoiceData.getInvoice());
        List<Line> savedLines = new ArrayList<>();
        invoiceData.getLines().getLine().forEach(lineType -> savedLines.add(lineService.saveLine(lineConverter.convert(lineType))));
        invoice.setLines(savedLines.stream().map(Line::getId).toList());
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);

        return invoiceDataTypeConverter.convert(savedInvoice, savedLines);
    }

    @Transactional
    public InvoiceDataType deleteInvoice(String id) throws BaseException {
        Invoice deletedInvoice = invoiceService.deleteInvoice(id);
        List<String> invoiceLineIds = deletedInvoice.getLines();
        List<Line> deletedInvoiceLines = lineService.getLines(invoiceLineIds);
        for (String invoiceLineId : invoiceLineIds) {
            if (!isLineAssignedToInvoice(invoiceLineId)) {
                lineService.deleteLine(invoiceLineId);
            }
        }

        return invoiceDataTypeConverter.convert(deletedInvoice, deletedInvoiceLines);
    }

    private void setLineListType(List<Line> lines, LineListType lineListType) {
        lines.forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));
    }

    private List<Line> getInvoiceLines(Invoice invoice) {
        return invoice.getLines() == null ? Collections.emptyList() : lineService.getLines(invoice.getLines());
    }

    private void setInvoiceDataListType(List<Invoice> invoices, InvoiceDataListType invoiceDataListType) {
        invoices.forEach(invoice -> invoiceDataListType.getInvoiceData().add(invoiceDataTypeConverter.convert(invoice, getInvoiceLines(invoice))));
    }

    private boolean isLineAssignedToInvoice(String lineId) {
        return invoiceService.getAllInvoiceLines().stream().anyMatch(line -> line.equals(lineId));
    }
}
