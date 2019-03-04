package com.volgagas.personalassistant.models.model.order;

/**
 * Created by CaramelHeaven on 15:52, 27/02/2019.
 * This is Requisition
 */
public class UserOrder {
    private String name;
    private String requisitionNumber;
    private String status;

    @Override
    public String toString() {
        return "UserOrder{" +
                "name='" + name + '\'' +
                ", requisitionNumber='" + requisitionNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
