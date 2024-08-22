package hu.icellmobilsoft.onboarding.java.sample.action;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.model.Sample;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SampleLineAction {

    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    public SampleLineAction(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
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

    public InvoiceData getInvoiceData(String id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findInvoice(id);
        return optionalInvoice.map(invoice -> new InvoiceData(
                        invoice,
                        invoice.getLines() == null
                                ? Collections.emptyList()
                                : invoice.getLines().stream().map(line -> lineRepository.findLine(line))
                                    .filter(Optional::isPresent).map(Optional::get).toList()
                )
        ).orElse(null);
    }

    public List<InvoiceData> getAllInvoicesData() {
        return invoiceRepository.getAllInvoices().stream()
                .map(invoice -> new InvoiceData(
                                invoice,
                                invoice.getLines().stream().map(line -> lineRepository.findLine(line))
                                        .filter(Optional::isPresent).map(Optional::get).toList()
                        )
                ).toList();
    }
}
