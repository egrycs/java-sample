package hu.icellmobilsoft.onboarding.java.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.UnitOfMeasureType;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Model
public class InvoiceService {

    @Inject
    private EntityManager em;

    public Invoice saveInvoice(Invoice invoice) {
        String invoiceId = invoice.getId();
        Invoice existingInvoice = null;

        if (invoiceId != null) {
            existingInvoice = findInvoice(invoiceId);
        }

        if (existingInvoice != null) {
            existingInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
            existingInvoice.setInvoiceType(invoice.getInvoiceType());
            existingInvoice.setSupplierTaxNumber(invoice.getSupplierTaxNumber());
            existingInvoice.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
            existingInvoice.setSumPrice(invoice.getSumPrice());
            em.persist(existingInvoice);
        } else {
            if (invoiceId == null) {
                String lastInvoiceId = getLastRecord();
                int nextInvoiceId = Integer.parseInt(lastInvoiceId) + 1;
                invoice.setId(String.format("%06d", nextInvoiceId));
            }
            em.persist(invoice);
        }

        return invoice;
    }

    public boolean isInvoiceDataTableEmpty() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        cq.from(Invoice.class);
        cq.select(cb.literal(1));
        List<Integer> result = em.createQuery(cq).setMaxResults(1).getResultList();

        return result.isEmpty();
    }

    public List<Invoice> getAllInvoices(InvoiceDataListQueryType invoiceDataListQuery) {
        String invoiceNumber = invoiceDataListQuery.getInvoiceNumber();
        String invoiceType = invoiceDataListQuery.getInvoiceType();
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> invoiceRoot = cq.from(Invoice.class);
        // invoiceRoot.fetch("lines", JoinType.LEFT); // ha az Invoice entitás lines elemén @ElementCollection(fetch = FetchType.LAZY)
        cq.select(invoiceRoot);

        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            predicates.add(cb.like(invoiceRoot.get("invoiceNumber"), "%" + invoiceNumber + "%"));
        }
        if (invoiceType != null && !invoiceType.isEmpty()) {
            predicates.add(cb.equal(invoiceRoot.get("invoiceType"), invoiceType));
        }
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(cq).getResultList();
    }

    public List<String> getAllInvoiceLines() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Invoice> invoiceRoot = cq.from(Invoice.class);
        cq.select(invoiceRoot.get("lines")).distinct(true);

        return em.createQuery(cq).getResultList();
    }

    public Invoice getInvoice(String id) throws BaseException {
        Invoice invoice = findInvoice(id);
        if (invoice == null) {
            throw new BaseException("Entity not found!");
        }

        return invoice;
    }

    public Invoice findInvoice(String id) {
        return em.find(Invoice.class, id);
    }

    public Invoice deleteInvoice(String id) throws BaseException {
        Invoice deletedInvoice = getInvoice(id);
        em.remove(deletedInvoice);

        return deletedInvoice;
    }

    private String getLastRecord() {
        String sql = "SELECT id FROM Invoice ORDER BY ROWID DESC FETCH FIRST 1 ROW ONLY";

        return (String) em.createNativeQuery(sql).getResultList().get(0);
    }
}
