package com.volgagas.personalassistant.models.model.order;

/**
 * Created by CaramelHeaven on 19:11, 27/02/2019.
 */
public class ServerOrder {
    private String purchaseOrderName;
    private String deliveryAddress;
    private String purchaseOrderNumber;

    public String getPurchaseOrderName() {
        return purchaseOrderName;
    }

    public void setPurchaseOrderName(String purchaseOrderName) {
        this.purchaseOrderName = purchaseOrderName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }
}
