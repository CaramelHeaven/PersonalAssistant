package com.volgagas.personalassistant.utils.channels.pass_data;

/**
 * Created by CaramelHeaven on 16:40, 12.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RequestData {
    private String title;
    private String description;
    private String endDate;
    private boolean isImportant;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isImportant=" + isImportant +
                '}';
    }
}
