package hu.icellmobilsoft.onboarding.java.sample.rest;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Model
public class InvoiceDataRest implements IInvoiceDataRest {

    @Inject
    private SampleLineAction sampleLineAction;

    public InvoiceDataResponse getInvoiceData(String id) throws BaseException {
        InvoiceDataResponse response = new InvoiceDataResponse();
        response.setInvoiceData(sampleLineAction.getInvoiceData(id));

        return response;
    };

    public InvoiceDataListQueryResponse invoiceDataQuery(InvoiceDataListQueryRequest request) throws BaseException {
        InvoiceDataListQueryType queryParams = request.getQueryParams();
        InvoiceDataListQueryOrderType orderParams = request.getOrderParams();
        QueryRequestDetails paginationParams = request.getPaginationParams();
        InvoiceDataListQueryResponse response = new InvoiceDataListQueryResponse();
        response.setInvoices(sampleLineAction.invoiceDataQuery(queryParams, orderParams, paginationParams));

        return response;
    };

    public InvoiceDataResponse postInvoiceData(InvoiceDataRequest request) {
        InvoiceDataType invoiceData = request.getInvoiceData();
        invoiceData.getInvoice().setId(null);
        InvoiceDataResponse response = new InvoiceDataResponse();
        response.setInvoiceData(sampleLineAction.saveInvoiceData(invoiceData));

        return response;
    }

    public InvoiceDataResponse putInvoiceData(String id, InvoiceDataRequest request) throws BaseException {
        InvoiceDataType invoiceData = request.getInvoiceData();
        getInvoiceData(id);
        invoiceData.getInvoice().setId(id); // a payload-ban az id nem szerepel, ezért külön be kell állítani
        InvoiceDataResponse response = new InvoiceDataResponse();
        response.setInvoiceData(sampleLineAction.saveInvoiceData(invoiceData));

        return response;
    }

    public InvoiceDataResponse deleteInvoice(String id) throws BaseException {
        InvoiceDataResponse response = new InvoiceDataResponse();
        response.setInvoiceData(sampleLineAction.deleteInvoice(id));

        return response;
    }
}
