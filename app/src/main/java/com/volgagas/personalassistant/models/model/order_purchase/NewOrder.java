package com.volgagas.personalassistant.models.model.order_purchase;

import android.content.Context;

/**
 * Created by CaramelHeaven on 16:07, 25/12/2018.
 * new orders for created it
 */
public class NewOrder {
    private String name;
    private int imageReference;

    //status newOrder. remove or add
    private int status;

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
}
