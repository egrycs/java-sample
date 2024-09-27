package hu.icellmobilsoft.onboarding.java.sample.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LineDeleteException;
import hu.icellmobilsoft.onboarding.java.sample.rest.LoadDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.rest.RequestDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.util.Validator;

public class SampleLineAction {

    private LineRepository lineRepository;
    private LoadDataImpl loadDataImpl;
    private RequestDataImpl requestDataImpl;

    public SampleLineAction(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.lineRepository = lineRepository;
        this.loadDataImpl = new LoadDataImpl(invoiceRepository, lineRepository);
        this.requestDataImpl = new RequestDataImpl(invoiceRepository, lineRepository);
    }

    public void loadFromXml(String xmlFileName, String schemaFileName) {
        URL xmlUrl = SampleLineAction.class.getClassLoader().getResource(xmlFileName);
        URL xsdUrl = SampleLineAction.class.getClassLoader().getResource(schemaFileName);
        Path xmlUri;
        Path xsdUri;
        String xmlString;
        try {
            xmlUri = Paths.get(xmlUrl.toURI());
            xsdUri = Paths.get(xsdUrl.toURI());
            xmlString = new String(Files.readAllBytes(xmlUri));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Validator.validateByXsd(xmlUri.toString(), xsdUri.toString());
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

    public InvoiceData getInvoiceData(String id) {
        return requestDataImpl.getInvoiceData(id);
    }

    public List<InvoiceData> getAllInvoicesData() {
        return requestDataImpl.getAllInvoicesData();
    }

    public List<InvoiceData> queryInvoicesData(String invoiceNumber, String invoiceType) {
        return this.getAllInvoicesData()
                .stream()
                .filter(
                        invoiceData -> invoiceData.getInvoice().getInvoiceNumber().equals(invoiceNumber)
                                || invoiceData.getInvoice().getInvoiceType().equals(invoiceType))
                .toList();
    }

    public Line deleteLine(String id) throws LineDeleteException { // LineDeleteException, EntityNotFoundException
        if (isLineAssignedToInvoice(id)) {
            throw new LineDeleteException("The line with id " + id + " is assigned to an invoice and cannot be deleted.");
        }
        return lineRepository.deleteLine(id);
    }

    private boolean isLineAssignedToInvoice(String lineId) {
        return this.getAllInvoicesData().stream().flatMap(invoiceData ->
            invoiceData.getInvoice().getLines() == null ? Stream.of() : invoiceData.getInvoice().getLines().stream()
        ).anyMatch(line -> line.equals(lineId));
    }
}
