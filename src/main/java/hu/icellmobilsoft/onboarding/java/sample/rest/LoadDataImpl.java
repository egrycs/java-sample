package hu.icellmobilsoft.onboarding.java.sample.rest;

import java.io.StringReader;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.Sample;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.LineConverter;

public class LoadDataImpl implements ILoadData {
    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    public LoadDataImpl(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineRepository = lineRepository;
    }

    public void loadFromXml(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Sample.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Sample sample = (Sample) unmarshaller.unmarshal(reader);

            InvoiceConverter invoiceConverter = new InvoiceConverter();
            List<Invoice> invoices = sample.getInvoices().getInvoice().stream().map(invoiceConverter::convert).toList();

            for (Invoice invoice : invoices) {
                invoiceRepository.saveInvoice(invoice);
            }

            LineConverter lineConverter = new LineConverter();
            List<Line> lines = sample.getLines().getLine().stream().map(lineConverter::convert).toList();

            for (Line line : lines) {
                lineRepository.saveLine(line);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJson(String json) {
        try {
            JsonMapper jsonMapper = new JsonMapper();
            hu.icellmobilsoft.onboarding.java.sample.model.Sample sample = jsonMapper
                    .readValue(json, hu.icellmobilsoft.onboarding.java.sample.model.Sample.class);

            for (Invoice invoice : sample.getInvoices()) {
                invoiceRepository.saveInvoice(invoice);
            }
            for (Line line : sample.getLines()) {
                lineRepository.saveLine(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
