package hu.icellmobilsoft.onboarding.java.sample.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class InvoiceData {
    @JacksonXmlElementWrapper(localName = "lines")
    @JacksonXmlProperty(localName = "line")
    private List<Line> lines;

    @JacksonXmlProperty(localName = "invoice")
    private Invoice invoice;

    public InvoiceData(Invoice invoice, List<Line> lines) {
        this.invoice = invoice;
        this.lines = lines;
    }

    // Getters and setters
    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoices(Invoice invoice) {
        this.invoice = invoice;
    }

    // Ez csak tesztre van használva
    @Override
    public String toString() {
        return '\n' + "\tInvoiceData{" +
                '\n' + "\t\tlines=" + lines + "," +
                '\n' + "\t\tinvoice=" + invoice +
                '\n' + "\t}";
    }
}