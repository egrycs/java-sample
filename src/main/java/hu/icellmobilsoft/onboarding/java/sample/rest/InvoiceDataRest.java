package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.java.sample.JavaSampleApp;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

public class InvoiceDataRest implements IInvoiceDataRest {

    private SampleLineAction sampleLineAction;

    public InvoiceDataRest() {
        sampleLineAction = JavaSampleApp.getSampleLineAction();
    }

    public InvoiceDataType getInvoiceData(String id) throws BaseException {
        return sampleLineAction.getInvoiceData(id);
    };

    public InvoiceDataListType invoiceDataQuery(InvoiceDataListQueryType invoiceListQuery) throws BaseException {
        return sampleLineAction.invoiceDataQuery(invoiceListQuery);
    };

    public InvoiceDataType postInvoiceData(InvoiceDataType invoiceData) {
        return sampleLineAction.saveInvoiceData(invoiceData);
    }

    public InvoiceDataType putInvoiceData(String id, InvoiceDataType invoiceData) throws BaseException {
        getInvoiceData(id);
        invoiceData.getInvoice().setId(id); // a payload-ban az id nem szerepel, ezért külön be kell állítani
        return sampleLineAction.modifyInvoiceData(invoiceData);
    }

    public InvoiceDataType deleteInvoice(String id) throws BaseException {
        return sampleLineAction.deleteInvoice(id);
    }
}
