package com.volgagas.personalassistant.models.model.worker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SubTask implements Parcelable, Comparable<SubTask> {
    private String description;
    private String endDate;

    private String worker;
    private String idSubTask;
    private String idActivity;
    private String status;

    /* formatting time is here
     * */
    private String startTime;

    /* formatting date is here
     * */
    private String startDate;

    /* server time without formatting
     * */
    private String startServerTime;

    //for make photo adapter
    private List<String> picturesPath;
    //for check boxes
    private boolean stateBox;

    //date for sorting subtasks
    private Date dateStart;

    //path file where we will send to server if it exist
    private String filePath;

    public SubTask(String description) {
        this.description = description;
    }

    public SubTask() {

    }

    @Override
    public String toString() {
        return "SubTask{" +
                "description='" + description + '\'' +
                ", endDate='" + endDate + '\'' +
                ", worker='" + worker + '\'' +
                ", idSubTask='" + idSubTask + '\'' +
                ", idActivity='" + idActivity + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startServerTime='" + startServerTime + '\'' +
                ", picturesPath=" + picturesPath +
                ", stateBox=" + stateBox +
                ", dateStart=" + dateStart +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @Override
    public int compareTo(SubTask o) {
        return getDateStart().compareTo(o.getDateStart());
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getStartServerTime() {
        return startServerTime;
    }

    public void setStartServerTime(String startServerTime) {
        this.startServerTime = startServerTime;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.endDate);
        dest.writeString(this.worker);
        dest.writeString(this.idSubTask);
        dest.writeString(this.idActivity);
        dest.writeString(this.status);
        dest.writeString(this.startTime);
        dest.writeString(this.startDate);
        dest.writeString(this.startServerTime);
        dest.writeStringList(this.picturesPath);
        dest.writeByte(this.stateBox ? (byte) 1 : (byte) 0);
        dest.writeLong(this.dateStart != null ? this.dateStart.getTime() : -1);
        dest.writeString(this.filePath);
    }

    protected SubTask(Parcel in) {
        this.description = in.readString();
        this.endDate = in.readString();
        this.worker = in.readString();
        this.idSubTask = in.readString();
        this.idActivity = in.readString();
        this.status = in.readString();
        this.startTime = in.readString();
        this.startDate = in.readString();
        this.startServerTime = in.readString();
        this.picturesPath = in.createStringArrayList();
        this.stateBox = in.readByte() != 0;
        long tmpDateStart = in.readLong();
        this.dateStart = tmpDateStart == -1 ? null : new Date(tmpDateStart);
        this.filePath = in.readString();
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
