package hu.icellmobilsoft.onboarding.java.sample.util;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceLineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceType;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;

public class InvoiceConverter {

    public Invoice convert(InvoiceType invoiceType) {
        Invoice invoice = new Invoice();

        invoice.setId(invoiceType.getId());
        invoice.setInvoiceNumber(invoiceType.getInvoiceNumber());
        invoice.setInvoiceType(invoiceType.getInvoiceType());
        invoice.setSupplierTaxNumber(invoiceType.getSupplierTaxNumber());
        invoice.setCustomerTaxNumber(invoiceType.getCustomerTaxNumber());
        if (invoiceType.getLines() != null) {
            invoice.setLines(invoiceType.getLines().getLine());
        }
        invoice.setSumPrice(invoiceType.getSumPrice());

        return invoice;
    }

    public InvoiceType convert(Invoice invoice) {
        InvoiceType invoiceType = new InvoiceType();

        invoiceType.setId(invoice.getId());
        invoiceType.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceType.setInvoiceType(invoice.getInvoiceType());
        invoiceType.setSupplierTaxNumber(invoice.getSupplierTaxNumber());
        invoiceType.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
        if (invoice.getLines() != null) {
            InvoiceLineListType invoiceLineListType = new InvoiceLineListType();
            invoice.getLines().forEach(line -> invoiceLineListType.getLine().add(line));
            invoiceType.setLines(invoiceLineListType);
        }
        invoiceType.setSumPrice(invoice.getSumPrice());

        return invoiceType;
    }

    public Invoice convert(InvoiceDataType invoiceDataType) {
        InvoiceConverter invoiceConverter = new InvoiceConverter();

        return invoiceConverter.convert(invoiceDataType.getInvoice());
    }
}
