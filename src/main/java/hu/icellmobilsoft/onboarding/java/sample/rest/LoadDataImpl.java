package hu.icellmobilsoft.onboarding.java.sample.rest;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.model.Sample;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;

public class LoadDataImpl implements ILoadData {
    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    public LoadDataImpl(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineRepository = lineRepository;
    }
    public void loadFromXml(String xml) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            Sample sample = xmlMapper.readValue(xml, Sample.class);

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

    public void loadFromJson(String json) {
        try {
            JsonMapper jsonMapper = new JsonMapper();
            Sample sample = jsonMapper.readValue(json, Sample.class);

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
