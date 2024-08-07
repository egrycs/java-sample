package hu.icellmobilsoft.onboarding.java.sample.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Invoice {
    private String id;
    private String invoiceNumber;
    private String invoiceType;
    private String supplierTaxNumber;
    private String customerTaxNumber;
    private int sumPrice;

    @JacksonXmlElementWrapper(localName = "lines")
    @JacksonXmlProperty(localName = "line")
    private List<String> lines;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getSupplierTaxNumber() {
        return supplierTaxNumber;
    }

    public void setSupplierTaxNumber(String supplierTaxNumber) {
        this.supplierTaxNumber = supplierTaxNumber;
    }

    public String getCustomerTaxNumber() {
        return customerTaxNumber;
    }

    public void setCustomerTaxNumber(String customerTaxNumber) {
        this.customerTaxNumber = customerTaxNumber;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", supplierTaxNumber='" + supplierTaxNumber + '\'' +
                ", customerTaxNumber='" + customerTaxNumber + '\'' +
                ", lines=" + lines +
                ", sumPrice=" + sumPrice +
                '}';
    }
}
