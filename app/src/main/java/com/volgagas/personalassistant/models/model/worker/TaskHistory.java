package com.volgagas.personalassistant.models.model.worker;

import android.os.Parcel;
import android.os.Parcelable;

import com.volgagas.personalassistant.models.model.common.GlobalTask;

import java.util.List;
import java.util.Objects;

/**
 * Created by CaramelHeaven on 17:39, 27/12/2018.
 */
public class TaskHistory implements Parcelable, GlobalTask {
    private String idTask;
    private String preferredTime;
    private String description;
    private String status;
    private List<SubTask> subTasks;
    private String gpa;

    //Minimal time from all subtasks
    private String startTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskHistory that = (TaskHistory) o;
        return Objects.equals(idTask, that.idTask);
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

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
        dest.writeString(this.startTime);
    }

    public TaskHistory() {
    }

    protected TaskHistory(Parcel in) {
        this.idTask = in.readString();
        this.preferredTime = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.subTasks = in.createTypedArrayList(SubTask.CREATOR);
        this.gpa = in.readString();
        this.startTime = in.readString();
    }

    public static final Creator<TaskHistory> CREATOR = new Creator<TaskHistory>() {
        @Override
        public TaskHistory createFromParcel(Parcel source) {
            return new TaskHistory(source);
        }

        @Override
        public TaskHistory[] newArray(int size) {
            return new TaskHistory[size];
        }
    };
}
