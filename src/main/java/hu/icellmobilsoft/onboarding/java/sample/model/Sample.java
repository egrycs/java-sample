package hu.icellmobilsoft.onboarding.java.sample.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Sample {
    @JacksonXmlElementWrapper(localName = "lines")
    @JacksonXmlProperty(localName = "line")
    private List<Line> lines;

    @JacksonXmlElementWrapper(localName = "invoices")
    @JacksonXmlProperty(localName = "invoice")
    private List<Invoice> invoices;

    // Getters and setters
    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "lines=" + lines +
                ", invoices=" + invoices +
                '}';
    }
}