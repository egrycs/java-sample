package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;

import java.util.List;

public interface IRequestData {
    InvoiceData getInvoiceData(String id);

    List<InvoiceData> getAllInvoicesData();
}
