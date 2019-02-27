package com.volgagas.personalassistant.models.model.order;

/**
 * Created by CaramelHeaven on 15:52, 27/02/2019.
 */
public class UserOrder {
    private String name;
    private String defaultBusinessName;
    private String description;
    private String startDate;
    private String endDate;
    private String requisitionNumber;
    private String status;

    @Override
    public String toString() {
        return "UserOrder{" +
                "name='" + name + '\'' +
                ", defaultBusinessName='" + defaultBusinessName + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
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

    public String getDefaultBusinessName() {
        return defaultBusinessName;
    }

    public void setDefaultBusinessName(String defaultBusinessName) {
        this.defaultBusinessName = defaultBusinessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
