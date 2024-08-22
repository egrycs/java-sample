package hu.icellmobilsoft.onboarding.java.sample.repository;

import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository {

    private List<Invoice> invoices;

    public InvoiceRepository() {
        invoices = new ArrayList<>();
    }

    public void saveInvoice(Invoice invoice) {
        Optional<Invoice> existingInvoice = findInvoice(invoice.getId());
        if (existingInvoice.isPresent()) {
            Invoice currentInvoice = existingInvoice.get();
            currentInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
            currentInvoice.setInvoiceType(invoice.getInvoiceType());
            currentInvoice.setSupplierTaxNumber(invoice.getSupplierTaxNumber());
            currentInvoice.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
            currentInvoice.setSumPrice(invoice.getSumPrice());
            currentInvoice.setLines(invoice.getLines());
        } else {
            invoices.add(invoice);
        }
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    public Optional<Invoice> findInvoice(String id) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findFirst();
    }
}
