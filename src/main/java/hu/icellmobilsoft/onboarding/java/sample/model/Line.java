package hu.icellmobilsoft.onboarding.java.sample.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "LINE")
public class Line {

    @Id
    @Size(max = 32)
    @NotNull
    @Column(name = "ID", length = 32, nullable = false, updatable = false)
    private String id;

    @Size(max = 50)
    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "QUANTITY", precision = 10, scale = 0)
    private int quantity;

    @Column(name = "UNIT_OF_MEASURE", length = 10)
    private String unitOfMeasure;

    @Size(max = 50)
    @Column(name = "CUSTOM_UNIT_OF_MEASURE", length = 50)
    private String customUnitOfMeasure;

    @Column(name = "UNIT_PRICE", precision = 15, scale = 2)
    private BigDecimal unitPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getCustomUnitOfMeasure() {
        return customUnitOfMeasure;
    }

    public void setCustomUnitOfMeasure(String customUnitOfMeasure) {
        this.customUnitOfMeasure = customUnitOfMeasure;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Ez csak tesztre van használva
    @Override
    public String toString() {
        return '\n' + "\t\t\t{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", quantity=" + quantity + ", unitOfMeasure='" + unitOfMeasure
                + '\'' + ", customUnitOfMeasure='" + customUnitOfMeasure + '\'' + ", unitPrice=" + unitPrice + '}';
    }
}
