package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;

public interface IRequestData {
    InvoiceDataType getInvoiceData(String id);

    InvoiceDataListType getAllInvoicesData();
}
