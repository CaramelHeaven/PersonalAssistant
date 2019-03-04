package com.volgagas.personalassistant.models.model.order;

/**
 * Created by CaramelHeaven on 15:52, 27/02/2019.
 */
public class UserSubOrder {
    private String status;
    private String unit;
    private String price;
    private String quantity;
    private String description;
    private String priceCode;

    @Override
    public String toString() {
        return "UserSubOrder{" +
                "status='" + status + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", description='" + description + '\'' +
                ", priceCode='" + priceCode + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }
}
