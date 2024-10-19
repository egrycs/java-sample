package hu.icellmobilsoft.onboarding.java.sample.action;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineType;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LoadDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.rest.RequestDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.util.*;

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

    public InvoiceDataType getInvoiceData(String id) {
        return requestDataImpl.getInvoiceData(id);
    }

    public InvoiceDataListType getAllInvoicesData() { return requestDataImpl.getAllInvoicesData(); }

    public InvoiceDataListType queryInvoicesData(String invoiceNumber, String invoiceType) {
        return requestDataImpl.queryInvoicesData(invoiceNumber, invoiceType);
    }

    public LineType getLine(String id) throws BaseException {
        return requestDataImpl.getLine(id);
    }

    public LineListType getAllLine() {
        return requestDataImpl.getAllLine();
    }

    public LineType saveLine(LineType line) {
        return requestDataImpl.saveLine(line);
    }

    public LineType deleteLine(String id) throws BaseException {
        return requestDataImpl.deleteLine(id);
    }
}
