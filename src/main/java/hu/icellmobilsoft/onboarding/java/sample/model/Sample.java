package hu.icellmobilsoft.onboarding.java.sample.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

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

    // Ez csak tesztre van használva
    @Override
    public String toString() {
        return '\n' + "Sample{" +
                '\n' + "\t\tlines=" + lines + "," +
                '\n' + "\t\tinvoices=" + invoices +
                '\n' + "\t}";
    }
}