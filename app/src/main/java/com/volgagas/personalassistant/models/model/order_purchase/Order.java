package com.volgagas.personalassistant.models.model.order_purchase;

/**
 * Created by CaramelHeaven on 11:33, 25/12/2018.
 * Orders for OrderActivity where users can find their clothes etc.
 */
public class Order {
    private String title;
    private boolean status;
    private String description;

    public Order(String title, boolean status, String description) {
        this.title = title;
        this.status = status;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
