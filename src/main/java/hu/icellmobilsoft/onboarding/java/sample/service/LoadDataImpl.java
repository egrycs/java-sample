package hu.icellmobilsoft.onboarding.java.sample.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import hu.icellmobilsoft.onboarding.java.sample.coffee.JaxbTool;
import hu.icellmobilsoft.onboarding.java.sample.converter.InvoiceConverter;
import hu.icellmobilsoft.onboarding.java.sample.converter.LineConverter;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseProcessingExceptionWrapper;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.util.JsonMessageBodyReaderBase;

@Model
public class LoadDataImpl implements ILoadData {

    @Inject
    private JaxbTool jaxbTool;

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private LineService lineService;

    @Transactional
    public void loadFromXml(InputStream xmlStream, String schemaFileName) {
        Sample sample;
        try {
            sample = this.jaxbTool.unmarshalXML(Sample.class, xmlStream, schemaFileName);
        } catch (BaseException e) {
            throw new BaseProcessingExceptionWrapper(e);
        }

        LineConverter lineConverter = new LineConverter();
        List<Line> lines = sample.getLines().getLine().stream().map(lineConverter::convert).toList();

        for (Line line : lines) {
            lineService.saveLine(line);
        }

        InvoiceConverter invoiceConverter = new InvoiceConverter();
        List<Invoice> invoices = sample.getInvoices().getInvoice().stream().map(invoiceConverter::convert).toList();

        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }
    }

    @Transactional
    public void loadFromJson(InputStream jsonStream, String schemaFileName) {
        hu.icellmobilsoft.onboarding.java.sample.model.Sample sample;
        LineConverter lineConverter = new LineConverter();
        InvoiceConverter invoiceConverter = new InvoiceConverter();
        try {
            sample = JsonMessageBodyReaderBase.toObjectGson(new InputStreamReader(jsonStream, StandardCharsets.UTF_8), hu.icellmobilsoft.onboarding.java.sample.model.Sample.class);
            SampleRequest sampleRequest = new SampleRequest();
            sampleRequest.setSample(new SampleType());
            sampleRequest.getSample().setLines(new LineListType());
            sampleRequest.getSample().setInvoices(new InvoiceListType());
            for (LineType lineType : sample.getLines().stream().map(lineConverter::convert).toList()) {
                sampleRequest.getSample().getLines().getLine().add(lineType);
            }
            for (InvoiceType invoiceType : sample.getInvoices().stream().map(invoiceConverter::convert).toList()) {
                sampleRequest.getSample().getInvoices().getInvoice().add(invoiceType);
            }
            this.jaxbTool.marshalXML(sampleRequest, schemaFileName);
        } catch (BaseException e) {
            throw new BaseProcessingExceptionWrapper(e);
        }


        List<Line> lines = sample.getLines();

        for (Line line : lines) {
            lineService.saveLine(line);
        }

        List<Invoice> invoices = sample.getInvoices();

        for (Invoice invoice : invoices) {
            invoiceService.saveInvoice(invoice);
        }
    }
}
