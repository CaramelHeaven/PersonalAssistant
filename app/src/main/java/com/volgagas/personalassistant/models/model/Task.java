package com.volgagas.personalassistant.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

/**
 * Created by CaramelHeaven on 17:39, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */

public class Task implements Parcelable {
    private String idTask;
    private String preferredTime;
    private String description;
    private String status;
    private List<SubTask> subTasks;
    private String gpa;

    @Override
    public String toString() {
        return "Task{" +
                "idTask='" + idTask + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", subTasks=" + subTasks +
                ", gpa='" + gpa + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(idTask, task.idTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask);
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getGpa() {
        return gpa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idTask);
        dest.writeString(this.preferredTime);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeTypedList(this.subTasks);
        dest.writeString(this.gpa);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.idTask = in.readString();
        this.preferredTime = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.subTasks = in.createTypedArrayList(SubTask.CREATOR);
        this.gpa = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
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