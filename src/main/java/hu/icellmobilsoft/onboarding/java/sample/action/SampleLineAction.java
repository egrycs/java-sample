package hu.icellmobilsoft.onboarding.java.sample.action;

import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LineDeleteException;
import hu.icellmobilsoft.onboarding.java.sample.rest.LoadDataImpl;
import hu.icellmobilsoft.onboarding.java.sample.rest.RequestDataImpl;

import java.util.List;
import java.util.stream.Stream;

public class SampleLineAction {

    private LineRepository lineRepository;
    private LoadDataImpl loadDataImpl;
    private RequestDataImpl requestDataImpl;

    public SampleLineAction(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.lineRepository = lineRepository;
        this.loadDataImpl = new LoadDataImpl(invoiceRepository, lineRepository);
        this.requestDataImpl = new RequestDataImpl(invoiceRepository, lineRepository);
    }

    public void loadFromXml(String xml) {
        loadDataImpl.loadFromXml(xml);
    }

    public void loadFromJson(String json) {
        loadDataImpl.loadFromJson(json);
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
