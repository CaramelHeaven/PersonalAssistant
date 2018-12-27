package com.volgagas.personalassistant.models.model.order_purchase;

import android.content.Context;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 16:07, 25/12/2018.
 * new orders for created it
 */
public class NewOrder {
    private String name;
    private int imageReference;

    //status newOrder. remove or add
    private int status;

    // size for order new bottom fragment
    private int sizeInSheet;

    public NewOrder(String name, int imageReference) {
        this.name = name;
        this.imageReference = imageReference;
    }

    @Override
    public String toString() {
        return "NewOrder{" +
                "name='" + name + '\'' +
                ", imageReference=" + imageReference +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewOrder order = (NewOrder) o;
        return imageReference == order.imageReference &&
                Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, imageReference);
    }

    public String getName() {
        return name;
    }

    public int getImageReference() {
        return imageReference;
    }

    public int getIntId(Context context, int imageInt) {
        String name = context.getResources().getResourceEntryName(imageInt);
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSizeInSheet() {
        return sizeInSheet;
    }

    public void setSizeInSheet(int sizeInSheet) {
        this.sizeInSheet = sizeInSheet;
    }
}
