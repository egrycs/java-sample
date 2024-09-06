package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RequestDataImpl implements IRequestData {

    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;

    public RequestDataImpl(InvoiceRepository invoiceRepository, LineRepository lineRepository) {
        this.invoiceRepository = invoiceRepository;
        this.lineRepository = lineRepository;
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
                                invoice.getLines() == null
                                        ? Collections.emptyList()
                                        : invoice.getLines().stream().map(line -> lineRepository.findLine(line))
                                        .filter(Optional::isPresent).map(Optional::get).toList()
                        )
                ).toList();
    }
}
