package hu.icellmobilsoft.onboarding.java.sample.model;

import java.util.List;

public class Sample {
    private List<Line> lines;

    private List<Invoice> invoices;

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