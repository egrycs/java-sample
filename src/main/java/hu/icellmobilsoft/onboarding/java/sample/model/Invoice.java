package hu.icellmobilsoft.onboarding.java.sample.model;

import java.math.BigDecimal;
import java.util.List;

public class Invoice {

    private String id;

    private String invoiceNumber;

    private String invoiceType;

    private String supplierTaxNumber;

    private String customerTaxNumber;

    private BigDecimal sumPrice;

    private List<String> lines;

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

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
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
