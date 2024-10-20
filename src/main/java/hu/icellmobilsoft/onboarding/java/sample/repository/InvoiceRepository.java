package hu.icellmobilsoft.onboarding.java.sample.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

public class InvoiceRepository {

    private List<Invoice> invoices;

    public InvoiceRepository() {
        invoices = new ArrayList<>();
    }

    public Invoice saveInvoice(Invoice invoice) {
        Optional<Invoice> existingInvoice = findInvoice(invoice.getId());
        if (existingInvoice.isPresent()) {
            Invoice currentInvoice = existingInvoice.get();
            currentInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
            currentInvoice.setInvoiceType(invoice.getInvoiceType());
            currentInvoice.setSupplierTaxNumber(invoice.getSupplierTaxNumber());
            currentInvoice.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
            currentInvoice.setSumPrice(invoice.getSumPrice());
            invoice.setLines(currentInvoice.getLines());
        } else {
            if (invoice.getId() == null) {
                String lastInvoiceId = invoices.get(invoices.size() - 1).getId();
                int nextInvoiceId = Integer.parseInt(lastInvoiceId) + 1;
                invoice.setId(String.format("%06d", nextInvoiceId));
            }
            invoices.add(invoice);
        }

        return invoice;
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    public Invoice getInvoice(String id) throws BaseException {
        Optional<Invoice> optionalInvoice = findInvoice(id);
        if (optionalInvoice.isEmpty()) {
            throw new BaseException("Entity not found!");
        }

        return optionalInvoice.get();
    }

    public Optional<Invoice> findInvoice(String id) {
        return invoices.stream().filter(invoice -> invoice.getId().equals(id)).findFirst();
    }

    public Invoice deleteInvoice(String id) throws BaseException {
        Invoice invoice = getInvoice(id);
        invoices.removeIf(line -> line.getId().equals(id));

        return invoice;
    }
}
