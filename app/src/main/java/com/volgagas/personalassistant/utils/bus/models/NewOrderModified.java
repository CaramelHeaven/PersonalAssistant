package com.volgagas.personalassistant.utils.bus.models;

import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;

/**
 * Created by CaramelHeaven on 14:06, 27/12/2018.
 * New order for callback from bottom sheet to new order view pager for set count = 0
 */
public class NewOrderModified {
    private NewOrder newOrder;
    private int lastCountState;

    public void setNewOrder(NewOrder newOrder) {
        this.newOrder = newOrder;
    }

    public NewOrder getNewOrder() {
        return newOrder;
    }

    public int getLastCountState() {
        return lastCountState;
    }

    public void setLastCountState(int lastCountState) {
        this.lastCountState = lastCountState;
    }
}
