package com.volgagas.personalassistant.models.model.order_purchase;

/**
 * Created by CaramelHeaven on 16:07, 25/12/2018.
 * new orders for created it
 */
public class NewOrder {
    private String name;
    private String imageReference;

    public NewOrder(String name, String imageReference) {
        this.name = name;
        this.imageReference = imageReference;
    }

    public String getName() {
        return name;
    }

    public String getImageReference() {
        return imageReference;
    }
}
