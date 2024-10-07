package hu.icellmobilsoft.onboarding.java.sample.model;

import java.math.BigDecimal;

public class Line {
    private String id;

    private String name;

    private int quantity;

    private String unitOfMeasure;

    private String customUnitOfMeasure;

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
        return '\n' + "\t\t\t{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", customUnitOfMeasure='" + customUnitOfMeasure + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
