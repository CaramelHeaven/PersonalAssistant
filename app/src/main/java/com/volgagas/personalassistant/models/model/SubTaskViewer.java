package com.volgagas.personalassistant.models.model;

public class SubTaskViewer {
    private String activityId;
    private String description;
    private String workerName;
    private String state;
    private String startTime;

    // boolean values used inside TaskDialogFragment which represent chosen current sub-tasks for
    // user. For example We can selected 2 from 4 common sub tasks, upload it and go away to
    // completed result screen
    private boolean working = false;

    @Override
    public String toString() {
        return "SubTaskViewer{" +
                "activityId='" + activityId + '\'' +
                ", description='" + description + '\'' +
                ", workerName='" + workerName + '\'' +
                ", state='" + state + '\'' +
                ", startTime='" + startTime + '\'' +
                ", working=" + working +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}