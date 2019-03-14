package com.volgagas.personalassistant.models.network.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 17:43, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskNetwork {
    @SerializedName("SO_ServiceOrder")
    @Expose
    private String serviceOrderId;
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String company;
    @SerializedName("SO_PreferredServiceTime")
    @Expose
    private String preferredTime;
    @SerializedName("SO_Description")
    @Expose
    private String serviceOrderDescription;
    @SerializedName("SO_ServiceStage")
    @Expose
    private String status;
    @SerializedName("AC_Description")
    @Expose
    private String activityDescription;
    @SerializedName("AC_ActivityEndDateTime")
    @Expose
    private String endDate;
    @SerializedName("AC_ActivityStartDateTime")
    @Expose
    private String startDate;
    @SerializedName("AC_Worker")
    @Expose
    private String worker;
    @SerializedName("AC_ServiceOrderLineNum")
    @Expose
    private Integer idSubTask;
    @SerializedName("AC_ActivityId")
    @Expose
    private String activityId;
    @SerializedName("SO_ServiceObjectId")
    @Expose
    private String gpa;
    @SerializedName("AC_Closed")
    @Expose
    private String acClosed;
    @SerializedName("SO_Category")
    @Expose
    private String projCategoryId;
    @SerializedName("SO_ServiceTaskId")
    @Expose
    private String soServiceTaskId;
    @SerializedName("SO_ProjectId")
    private String soProjectId;

    @Override
    public String toString() {
        return "TaskNetwork{" +
                "serviceOrderId='" + serviceOrderId + '\'' +
                ", odataEtag='" + odataEtag + '\'' +
                ", company='" + company + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                ", serviceOrderDescription='" + serviceOrderDescription + '\'' +
                ", status='" + status + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", worker='" + worker + '\'' +
                ", idSubTask=" + idSubTask +
                ", activityId='" + activityId + '\'' +
                ", gpa='" + gpa + '\'' +
                ", acClosed='" + acClosed + '\'' +
                ", projCategoryId='" + projCategoryId + '\'' +
                ", soServiceTaskId='" + soServiceTaskId + '\'' +
                ", soProjectId='" + soProjectId + '\'' +
                '}';
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getCompany() {
        return company;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public String getServiceOrderDescription() {
        return serviceOrderDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getWorker() {
        return worker;
    }

    public Integer getIdSubTask() {
        return idSubTask;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getGpa() {
        return gpa;
    }

    public String getAcClosed() {
        return acClosed;
    }

    public String getProjCategoryId() {
        return projCategoryId;
    }

    public String getSoServiceTaskId() {
        return soServiceTaskId;
    }

    public String getSoProjectId() {
        return soProjectId;
    }
}