package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

public interface IRequestData {
    InvoiceDataType getInvoiceData(String id) throws BaseException;

    InvoiceDataListType getAllInvoiceData();
}
