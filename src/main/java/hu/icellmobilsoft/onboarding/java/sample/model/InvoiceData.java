package hu.icellmobilsoft.onboarding.java.sample.model;

import java.util.List;

// Csak tesztre van használva
public class InvoiceData {
    private List<Line> lines;

    private Invoice invoice;

    public InvoiceData(Invoice invoice, List<Line> lines) {
        this.invoice = invoice;
        this.lines = lines;
    }

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

    @Override
    public String toString() {
        return '\n' + "\tInvoiceData{" +
                '\n' + "\t\tlines=" + lines + "," +
                '\n' + "\t\tinvoice=" + invoice +
                '\n' + "\t}";
    }
}