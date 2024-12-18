package hu.icellmobilsoft.onboarding.java.sample.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @Size(max = 32)
    @NotNull
    @Column(name = "ID", length = 32, nullable = false, updatable = false)
    private String id;

    @Size(max = 50)
    @Column(name = "INVOICE_NUMBER", length = 50)
    private String invoiceNumber;

    @Size(max = 50)
    @Column(name = "INVOICE_TYPE", length = 50)
    private String invoiceType;

    @Size(max = 50)
    @Column(name = "SUPPLIER_TAX_NUMBER", length = 50)
    private String supplierTaxNumber;

    @Size(max = 50)
    @Column(name = "CUSTOMER_TAX_NUMBER", length = 50)
    private String customerTaxNumber;

    @Column(name = "SUM_PRICE", precision = 15, scale = 2)
    private BigDecimal sumPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "INVOICELINE", joinColumns = @JoinColumn(name = "INVOICE_ID"))
    @Column(name = "LINE_ID")
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
        return '\n' + "\t\t\t{" + "id='" + id + '\'' + ", invoiceNumber='" + invoiceNumber + '\'' + ", invoiceType='" + invoiceType + '\''
                + ", supplierTaxNumber='" + supplierTaxNumber + '\'' + ", customerTaxNumber='" + customerTaxNumber + '\'' + ", lines=" + lines
                + ", sumPrice=" + sumPrice + '}';
    }
}
