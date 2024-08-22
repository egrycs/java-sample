package hu.icellmobilsoft.onboarding.java.sample.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Line {
    @JacksonXmlProperty(localName = "id")
    private String id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "quantity")
    private int quantity;

    @JacksonXmlProperty(localName = "unitOfMeasure")
    private String unitOfMeasure;

    @JacksonXmlProperty(localName = "customUnitOfMeasure")
    private String customUnitOfMeasure;

    @JacksonXmlProperty(localName = "unitPrice")
    private double unitPrice;

    // Getters and setters
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
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
