package hu.icellmobilsoft.onboarding.java.sample.action;

import java.io.FileNotFoundException;
import java.io.InputStream;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.service.LoadDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.service.RequestDataImpl;

@Model
public class SampleLineAction {

    @Inject
    private LoadDataImpl loadDataImpl;
    @Inject
    private RequestDataImpl requestDataImpl;

    public String loadFromXml(String xmlFileName, String schemaFileName) {
        String msg;

        if (requestDataImpl.isInvoiceDataTableEmpty()) {
            InputStream xmlStream = SampleLineAction.class.getClassLoader().getResourceAsStream(xmlFileName);
            try {
                if (xmlStream == null) {
                    throw new FileNotFoundException("Cannot find file: " + xmlFileName);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            loadDataImpl.loadFromXml(xmlStream, schemaFileName);

            msg = "The data has been loaded.";
        } else {
            msg = "The data has already been uploaded previously.";
        }

        return msg;
    }

    public String loadFromJson(String jsonFileName, String schemaFileName) {
        String msg;

        if (requestDataImpl.isInvoiceDataTableEmpty()) {
            InputStream jsonStream = SampleLineAction.class.getClassLoader().getResourceAsStream(jsonFileName);
            try {
                if (jsonStream == null) {
                    throw new FileNotFoundException("Cannot find file: " + jsonFileName);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            loadDataImpl.loadFromJson(jsonStream, schemaFileName);

            msg = "The data has been loaded.";
        } else {
            msg = "The data has already been uploaded previously.";
        }

        return msg;
    }

    public LineType getLine(String id) throws BaseException {
        return requestDataImpl.getLine(id);
    }

    public LineListType lineQuery(LineListQueryType queryParams, LineListQueryOrderType orderParams, QueryRequestDetails paginationParams)
            throws BaseException {
        return requestDataImpl.queryLine(queryParams, orderParams, paginationParams);
    }

    public LineType saveLine(LineType line) {
        return requestDataImpl.saveLine(line);
    }

    public LineType deleteLine(String id) throws BaseException {
        return requestDataImpl.deleteLine(id);
    }

    public InvoiceDataType getInvoiceData(String id) throws BaseException {
        return requestDataImpl.getInvoiceData(id);
    }

    public InvoiceDataListType getAllInvoiceData() {
        return requestDataImpl.getAllInvoiceData();
    }

    public InvoiceDataListType invoiceDataQuery(InvoiceDataListQueryType queryParams, InvoiceDataListQueryOrderType orderParams,
            QueryRequestDetails paginationParams) throws BaseException {
        return requestDataImpl.queryInvoicesData(queryParams, orderParams, paginationParams);
    }

    public InvoiceDataType saveInvoiceData(InvoiceDataType invoiceData) {
        return requestDataImpl.saveInvoiceData(invoiceData);
    }

    public InvoiceDataType deleteInvoice(String id) throws BaseException {
        return requestDataImpl.deleteInvoice(id);
    }
}
