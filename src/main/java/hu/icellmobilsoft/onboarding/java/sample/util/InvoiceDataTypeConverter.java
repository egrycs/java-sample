package hu.icellmobilsoft.onboarding.java.sample.util;

import java.util.List;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListType;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;

public class InvoiceDataTypeConverter {

    InvoiceConverter invoiceConverter;
    LineConverter lineConverter;

    public InvoiceDataTypeConverter() {
        invoiceConverter = new InvoiceConverter();
        lineConverter = new LineConverter();
    }

    public InvoiceDataType convert(Invoice invoice, List<Line> lines) {
        InvoiceDataType invoiceDataType = new InvoiceDataType();

        invoiceDataType.setInvoice(invoiceConverter.convert(invoice));
        LineListType lineListType = new LineListType();
        lines.forEach(line -> lineListType.getLine().add(lineConverter.convert(line)));
        invoiceDataType.setLines(lineListType);

        return invoiceDataType;
    }

    public InvoiceData convert(InvoiceDataType invoiceDataType) {
        return new InvoiceData(
                invoiceConverter.convert(invoiceDataType.getInvoice()),
                invoiceDataType.getLines().getLine().stream().map(lineConverter::convert).toList());
    }
}
