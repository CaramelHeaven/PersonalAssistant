package com.volgagas.personalassistant.models.network.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 17:43, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("ServiceOrderId")
    @Expose
    private String serviceOrderId;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ActivityTypeId")
    @Expose
    private String activityTypeId;
    @SerializedName("ProjId")
    @Expose
    private String projId;
    @SerializedName("OrderCreatedDateTime")
    @Expose
    private String orderCreatedDateTime;
    @SerializedName("StageId")
    @Expose
    private String stageId;
    @SerializedName("CustAccount")
    @Expose
    private String custAccount;
    @SerializedName("HcmWorker_PreferredTechnician_PersonnelNumber")
    @Expose
    private String hcmWorkerPreferredTechnicianPersonnelNumber;
    @SerializedName("OrderCreatedBy")
    @Expose
    private String orderCreatedBy;
    @SerializedName("ServiceDateTime")
    @Expose
    private String serviceDateTime;
    @SerializedName("HcmWorker_Responsible_PersonnelNumber")
    @Expose
    private String hcmWorkerResponsiblePersonnelNumber;
    @SerializedName("AgreementId")
    @Expose
    private String agreementId;
    @SerializedName("ProjCategoryId")
    @Expose
    private String projCategoryId;
    @SerializedName("CostCenter")
    @Expose
    private String costCenter;
    @SerializedName("ActivityStartDateTime")
    @Expose
    private String activityStartDateTime;
    @SerializedName("ServiceObjectId")
    @Expose
    private String serviceObjectId;
    @SerializedName("ActivityEndDateTime")
    @Expose
    private String activityEndDateTime;
    @SerializedName("ServiceTaskId")
    @Expose
    private String serviceTaskId;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public String getDescription() {
        return description;
    }

    public String getActivityTypeId() {
        return activityTypeId;
    }

    public String getProjId() {
        return projId;
    }

    public String getOrderCreatedDateTime() {
        return orderCreatedDateTime;
    }

    public String getStageId() {
        return stageId;
    }

    public String getCustAccount() {
        return custAccount;
    }

    public String getHcmWorkerPreferredTechnicianPersonnelNumber() {
        return hcmWorkerPreferredTechnicianPersonnelNumber;
    }

    public String getOrderCreatedBy() {
        return orderCreatedBy;
    }

    public String getServiceDateTime() {
        return serviceDateTime;
    }

    public String getHcmWorkerResponsiblePersonnelNumber() {
        return hcmWorkerResponsiblePersonnelNumber;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public String getProjCategoryId() {
        return projCategoryId;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public String getActivityStartDateTime() {
        return activityStartDateTime;
    }

    public String getServiceObjectId() {
        return serviceObjectId;
    }

    public String getActivityEndDateTime() {
        return activityEndDateTime;
    }

    public String getServiceTaskId() {
        return serviceTaskId;
    }
}
