package com.volgagas.personalassistant.models.model.queries;

import android.os.Parcel;

/**
 * Created by CaramelHeaven on 13:19, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * This is query FROM user in the uniform requests
 */
public class UniformRequest extends QueryBase {
    private String userName;
    private String title;
    private String description;
    private String endedTime;
    private String priority;

    @Override
    public String toString() {
        return "UniformRequest{" +
                "userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endedTime='" + endedTime + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getEndedTime() {
        return endedTime;
    }

    public void setEndedTime(String endedTime) {
        this.endedTime = endedTime;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.endedTime);
        dest.writeString(this.priority);
    }

    public UniformRequest() {
    }

    protected UniformRequest(Parcel in) {
        this.userName = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.endedTime = in.readString();
        this.priority = in.readString();
    }

    public static final Creator<UniformRequest> CREATOR = new Creator<UniformRequest>() {
        @Override
        public UniformRequest createFromParcel(Parcel source) {
            return new UniformRequest(source);
        }

        @Override
        public UniformRequest[] newArray(int size) {
            return new UniformRequest[size];
        }
    };
}
