package com.volgagas.personalassistant.models.model.kiosk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CaramelHeaven on 18:00, 27/12/2018.
 */
public class TaskTemplate implements Parcelable {
    private String dataAreaId;
    private String serviceOrderId;
    private String hcmResponsiblePersonnelNumber;
    private String description;
    private String activityTypeId;
    private String preferredTechnicianPersonnelNumber;
    private String stageId;
    private String projectId;
    private String serviceDateTime;
    private String categoryId;
    private Long workerResponsible;
    private String serviceTaskId;
    private String activityStartDateTime;
    private String custAccount;
    private String activityEndDateTime;
    private String serviceObjectId;
    private String agreementId;
    private String custCenter;

    private String startDateTime;
    private String endDateTime;
    private String workerPreferredTechnicianName;

    public TaskTemplate(String description) {
        this.description = description;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getHcmResponsiblePersonnelNumber() {
        return hcmResponsiblePersonnelNumber;
    }

    public void setHcmResponsiblePersonnelNumber(String hcmResponsiblePersonnelNumber) {
        this.hcmResponsiblePersonnelNumber = hcmResponsiblePersonnelNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getPreferredTechnicianPersonnelNumber() {
        return preferredTechnicianPersonnelNumber;
    }

    public void setPreferredTechnicianPersonnelNumber(String preferredTechnicianPersonnelNumber) {
        this.preferredTechnicianPersonnelNumber = preferredTechnicianPersonnelNumber;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getServiceDateTime() {
        return serviceDateTime;
    }

    public void setServiceDateTime(String serviceDateTime) {
        this.serviceDateTime = serviceDateTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getWorkerResponsible() {
        return workerResponsible;
    }

    public void setWorkerResponsible(Long workerResponsible) {
        this.workerResponsible = workerResponsible;
    }

    public String getServiceTaskId() {
        return serviceTaskId;
    }

    public void setServiceTaskId(String serviceTaskId) {
        this.serviceTaskId = serviceTaskId;
    }

    public String getActivityStartDateTime() {
        return activityStartDateTime;
    }

    public void setActivityStartDateTime(String activityStartDateTime) {
        this.activityStartDateTime = activityStartDateTime;
    }

    public String getCustAccount() {
        return custAccount;
    }

    public void setCustAccount(String custAccount) {
        this.custAccount = custAccount;
    }

    public String getActivityEndDateTime() {
        return activityEndDateTime;
    }

    public void setActivityEndDateTime(String activityEndDateTime) {
        this.activityEndDateTime = activityEndDateTime;
    }

    public String getServiceObjectId() {
        return serviceObjectId;
    }

    public void setServiceObjectId(String serviceObjectId) {
        this.serviceObjectId = serviceObjectId;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getCustCenter() {
        return custCenter;
    }

    public void setCustCenter(String custCenter) {
        this.custCenter = custCenter;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getWorkerPreferredTechnicianName() {
        return workerPreferredTechnicianName;
    }

    public void setWorkerPreferredTechnicianName(String workerPreferredTechnicianName) {
        this.workerPreferredTechnicianName = workerPreferredTechnicianName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataAreaId);
        dest.writeString(this.serviceOrderId);
        dest.writeString(this.hcmResponsiblePersonnelNumber);
        dest.writeString(this.description);
        dest.writeString(this.activityTypeId);
        dest.writeString(this.preferredTechnicianPersonnelNumber);
        dest.writeString(this.stageId);
        dest.writeString(this.projectId);
        dest.writeString(this.serviceDateTime);
        dest.writeString(this.categoryId);
        dest.writeValue(this.workerResponsible);
        dest.writeString(this.serviceTaskId);
        dest.writeString(this.activityStartDateTime);
        dest.writeString(this.custAccount);
        dest.writeString(this.activityEndDateTime);
        dest.writeString(this.serviceObjectId);
        dest.writeString(this.agreementId);
        dest.writeString(this.custCenter);
        dest.writeString(this.startDateTime);
        dest.writeString(this.endDateTime);
        dest.writeString(this.workerPreferredTechnicianName);
    }

    public TaskTemplate() {
    }

    protected TaskTemplate(Parcel in) {
        this.dataAreaId = in.readString();
        this.serviceOrderId = in.readString();
        this.hcmResponsiblePersonnelNumber = in.readString();
        this.description = in.readString();
        this.activityTypeId = in.readString();
        this.preferredTechnicianPersonnelNumber = in.readString();
        this.stageId = in.readString();
        this.projectId = in.readString();
        this.serviceDateTime = in.readString();
        this.categoryId = in.readString();
        this.workerResponsible = (Long) in.readValue(Long.class.getClassLoader());
        this.serviceTaskId = in.readString();
        this.activityStartDateTime = in.readString();
        this.custAccount = in.readString();
        this.activityEndDateTime = in.readString();
        this.serviceObjectId = in.readString();
        this.agreementId = in.readString();
        this.custCenter = in.readString();
        this.startDateTime = in.readString();
        this.endDateTime = in.readString();
        this.workerPreferredTechnicianName = in.readString();
    }

    public static final Parcelable.Creator<TaskTemplate> CREATOR = new Parcelable.Creator<TaskTemplate>() {
        @Override
        public TaskTemplate createFromParcel(Parcel source) {
            return new TaskTemplate(source);
        }

        @Override
        public TaskTemplate[] newArray(int size) {
            return new TaskTemplate[size];
        }
    };
}
