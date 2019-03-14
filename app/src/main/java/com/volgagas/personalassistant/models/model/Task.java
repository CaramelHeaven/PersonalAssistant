package com.volgagas.personalassistant.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.volgagas.personalassistant.models.model.common.GlobalTask;
import com.volgagas.personalassistant.models.model.worker.SubTask;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by CaramelHeaven on 17:39, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */

public class Task implements Parcelable, GlobalTask, Comparable<Task> {
    private String idTask;
    private String preferredTime;
    private String description;
    private String status;
    private List<SubTask> subTasks;
    private String gpa;
    private String projCategoryId;

    //date and time for reflect this data on card view
    private String startDate;
    private String startTime;

    //field for sorting tasks
    private Date serverDateTime;

    //feilds for reflect data in worker today
    private String dayOfMonth;
    private String dayOfWeek;

    //below two lines for nomenclatures
    private String serviceTaskId;
    private String soProjId;

    @Override
    public int compareTo(Task o) {
        return serverDateTime.compareTo(o.getServerDateTime());
    }

    @Override
    public String toString() {
        return "Task{" +
                "idTask='" + idTask + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", subTasks=" + subTasks +
                ", gpa='" + gpa + '\'' +
                ", projCategoryId='" + projCategoryId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", serverDateTime=" + serverDateTime +
                ", dayOfMonth='" + dayOfMonth + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", serviceTaskId='" + serviceTaskId + '\'' +
                ", soProjId='" + soProjId + '\'' +
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

    public Date getServerDateTime() {
        return serverDateTime;
    }

    public void setServerDateTime(Date serverDateTime) {
        this.serverDateTime = serverDateTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getSoProjId() {
        return soProjId;
    }

    public void setSoProjId(String soProjId) {
        this.soProjId = soProjId;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getProjCategoryId() {
        return projCategoryId;
    }

    public void setProjCategoryId(String projCategoryId) {
        this.projCategoryId = projCategoryId;
    }

    public String getServiceTaskId() {
        return serviceTaskId;
    }

    public void setServiceTaskId(String serviceTaskId) {
        this.serviceTaskId = serviceTaskId;
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
        dest.writeString(this.projCategoryId);
        dest.writeString(this.startDate);
        dest.writeString(this.startTime);
        dest.writeLong(this.serverDateTime != null ? this.serverDateTime.getTime() : -1);
        dest.writeString(this.dayOfMonth);
        dest.writeString(this.dayOfWeek);
        dest.writeString(this.serviceTaskId);
        dest.writeString(this.soProjId);
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
        this.projCategoryId = in.readString();
        this.startDate = in.readString();
        this.startTime = in.readString();
        long tmpServerDateTime = in.readLong();
        this.serverDateTime = tmpServerDateTime == -1 ? null : new Date(tmpServerDateTime);
        this.dayOfMonth = in.readString();
        this.dayOfWeek = in.readString();
        this.serviceTaskId = in.readString();
        this.soProjId = in.readString();
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