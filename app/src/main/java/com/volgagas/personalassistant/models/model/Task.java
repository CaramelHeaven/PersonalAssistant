package com.volgagas.personalassistant.models.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CaramelHeaven on 17:39, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Task implements Parcelable {
    private String serviceOrderId;
    private String description;
    private String categoryId;

    @Override
    public String toString() {
        return "Task{" +
                "serviceOrderId='" + serviceOrderId + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serviceOrderId);
        dest.writeString(this.description);
        dest.writeString(this.categoryId);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.serviceOrderId = in.readString();
        this.description = in.readString();
        this.categoryId = in.readString();
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
