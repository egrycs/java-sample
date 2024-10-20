package hu.icellmobilsoft.onboarding.java.sample.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LoadDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.rest.RequestDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.util.ByteArrayToFileConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.InputStreamConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.Validator;

public class SampleLineAction {

    private LoadDataImpl loadDataImpl;
    private RequestDataImpl requestDataImpl;

    public SampleLineAction(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.loadDataImpl = new LoadDataImpl(invoiceRepository, lineRepository);
        this.requestDataImpl = new RequestDataImpl(invoiceRepository, lineRepository);
    }

    public void loadFromXml(String xmlFileName, String schemaFileName) {
        InputStream xmlStream = SampleLineAction.class.getClassLoader().getResourceAsStream(xmlFileName);
        InputStream xsdStream = SampleLineAction.class.getClassLoader().getResourceAsStream(schemaFileName);
        String xmlString;
        File xml;
        File xsd;
        try {
            if (xmlStream == null) {
                throw new FileNotFoundException("Cannot find file: " + xmlFileName);
            }
            if (xsdStream == null) {
                throw new FileNotFoundException("Cannot find file: " + schemaFileName);
            }
            byte[] xmlByteArray = InputStreamConverter.convert(xmlStream);
            byte[] xsdByteArray = InputStreamConverter.convert(xsdStream);
            xmlString = new String(xmlByteArray, StandardCharsets.UTF_8);
            xml = ByteArrayToFileConverter.convert(xmlByteArray, xmlFileName);
            xsd = ByteArrayToFileConverter.convert(xsdByteArray, schemaFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Validator.validateByXsd(xml, xsd);
        loadDataImpl.loadFromXml(xmlString);
    }

    public void loadFromJson(String jsonFileName) {
        URL jsonUrl = SampleLineAction.class.getClassLoader().getResource(jsonFileName);
        Path jsonUri;
        String jsonString;
        try {
            jsonUri = Paths.get(jsonUrl.toURI());
            jsonString = new String(Files.readAllBytes(jsonUri));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        loadDataImpl.loadFromJson(jsonString);
    }

    public LineType getLine(String id) throws BaseException {
        return requestDataImpl.getLine(id);
    }

    public LineListType lineQuery(LineListQueryType lineListQuery) throws BaseException {
        return requestDataImpl.queryLine(lineListQuery);
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

    public InvoiceDataListType invoiceDataQuery(InvoiceDataListQueryType invoiceListQuery) throws BaseException {
        return requestDataImpl.queryInvoicesData(invoiceListQuery);
    }

    public InvoiceDataType saveInvoiceData(InvoiceDataType invoiceData) {
        return requestDataImpl.saveInvoiceData(invoiceData);
    }

    public InvoiceDataType modifyInvoiceData(InvoiceDataType invoiceData) {
        return requestDataImpl.modifyInvoiceData(invoiceData);
    }

    public InvoiceDataType deleteInvoice(String id) throws BaseException {
        return requestDataImpl.deleteInvoice(id);
    }
}
