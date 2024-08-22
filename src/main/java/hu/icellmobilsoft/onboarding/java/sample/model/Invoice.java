package hu.icellmobilsoft.onboarding.java.sample.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    @JacksonXmlProperty(localName = "id")
    private String id;

    @JacksonXmlProperty(localName = "invoiceNumber")
    private String invoiceNumber;

    @JacksonXmlProperty(localName = "invoiceType")
    private String invoiceType;

    @JacksonXmlProperty(localName = "supplierTaxNumber")
    private String supplierTaxNumber;

    @JacksonXmlProperty(localName = "customerTaxNumber")
    private String customerTaxNumber;


    @JacksonXmlProperty(localName = "sumPrice")
    private double sumPrice;

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

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    // Ez csak tesztre van használva
    @Override
    public String toString() {
        return '\n' + "\t\t\t{" +
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
