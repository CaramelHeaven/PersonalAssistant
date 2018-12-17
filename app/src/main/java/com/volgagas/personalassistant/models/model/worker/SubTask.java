package com.volgagas.personalassistant.models.model.worker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class SubTask implements Parcelable {
    private String description;
    private String endDate;
    private String startDate;
    private String worker;
    private String idSubTask;
    private String idActivity;
    private String status;
    private String startTime;

    //for make photo adapter
    private List<String> picturesPath;
    //for check boxes
    private boolean stateBox;

    public SubTask(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "description='" + description + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", worker='" + worker + '\'' +
                ", idSubTask='" + idSubTask + '\'' +
                ", idActivity='" + idActivity + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", picturesPath=" + picturesPath +
                ", stateBox=" + stateBox +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask subTask = (SubTask) o;
        return Objects.equals(description, subTask.description) &&
                Objects.equals(endDate, subTask.endDate) &&
                Objects.equals(startDate, subTask.startDate) &&
                Objects.equals(worker, subTask.worker) &&
                Objects.equals(idSubTask, subTask.idSubTask) &&
                Objects.equals(idActivity, subTask.idActivity) &&
                Objects.equals(status, subTask.status) &&
                Objects.equals(startTime, subTask.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, endDate, startDate, worker, idSubTask, idActivity, status, startTime);
    }

    public void setPicturesPath(List<String> picturesPath) {
        this.picturesPath = picturesPath;
    }

    public List<String> getPicturesPath() {
        return picturesPath;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(String idSubTask) {
        this.idSubTask = idSubTask;
    }

    public String getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isStateBox() {
        return stateBox;
    }

    public void setStateBox(boolean stateBox) {
        this.stateBox = stateBox;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.endDate);
        dest.writeString(this.startDate);
        dest.writeString(this.worker);
        dest.writeString(this.idSubTask);
        dest.writeString(this.idActivity);
        dest.writeString(this.status);
        dest.writeString(this.startTime);
        dest.writeStringList(this.picturesPath);
        dest.writeByte(this.stateBox ? (byte) 1 : (byte) 0);
    }

    public SubTask() {
    }

    protected SubTask(Parcel in) {
        this.description = in.readString();
        this.endDate = in.readString();
        this.startDate = in.readString();
        this.worker = in.readString();
        this.idSubTask = in.readString();
        this.idActivity = in.readString();
        this.status = in.readString();
        this.startTime = in.readString();
        this.picturesPath = in.createStringArrayList();
        this.stateBox = in.readByte() != 0;
    }

    public static final Creator<SubTask> CREATOR = new Creator<SubTask>() {
        @Override
        public SubTask createFromParcel(Parcel source) {
            return new SubTask(source);
        }

        @Override
        public SubTask[] newArray(int size) {
            return new SubTask[size];
        }
    };
}
