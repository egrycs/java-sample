package hu.icellmobilsoft.onboarding.java.sample.model;

class Line {
    private String id;
    private String name;
    private int quantity;
    private String unitOfMeasure;
    private String customUnitOfMeasure;
    private int unitPrice;

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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", customUnitOfMeasure='" + customUnitOfMeasure + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
