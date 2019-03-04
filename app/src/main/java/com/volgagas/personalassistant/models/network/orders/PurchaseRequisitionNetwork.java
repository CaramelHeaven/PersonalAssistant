package com.volgagas.personalassistant.models.network.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 15:39, 27/02/2019.
 */
public class PurchaseRequisitionNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("RequisitionNumber")
    @Expose
    private String requisitionNumber;
    @SerializedName("DefaultAccountingDate")
    @Expose
    private String defaultAccountingDate;
    @SerializedName("RequisitionPurpose")
    @Expose
    private String requisitionPurpose;
    @SerializedName("DefaultBusinessJustificationDetails")
    @Expose
    private String defaultBusinessJustificationDetails;
    @SerializedName("DefaultRequestedDate")
    @Expose
    private String defaultRequestedDate;
    @SerializedName("RequisitionName")
    @Expose
    private String requisitionName;
    @SerializedName("PreparerPersonnelNumber")
    @Expose
    private String preparerPersonnelNumber;
    @SerializedName("DefaultBusinessJustificationCode")
    @Expose
    private String defaultBusinessJustificationCode;
    @SerializedName("RequisitionStatus")
    @Expose
    private String requisitionStatus;
    @SerializedName("ProjectBuyingLegalEntityId")
    @Expose
    private String projectBuyingLegalEntityId;
    @SerializedName("IsPurchaseRequisitionOnHold")
    @Expose
    private String isPurchaseRequisitionOnHold;
    @SerializedName("DefaultProjectId")
    @Expose
    private String defaultProjectId;
    @SerializedName("OnHoldExplanation")
    @Expose
    private String onHoldExplanation;

    @Override
    public String toString() {
        return "PurchaseRequestionNetwork{" +
                "odataEtag='" + odataEtag + '\'' +
                ", requisitionNumber='" + requisitionNumber + '\'' +
                ", defaultAccountingDate='" + defaultAccountingDate + '\'' +
                ", requisitionPurpose='" + requisitionPurpose + '\'' +
                ", defaultBusinessJustificationDetails='" + defaultBusinessJustificationDetails + '\'' +
                ", defaultRequestedDate='" + defaultRequestedDate + '\'' +
                ", requisitionName='" + requisitionName + '\'' +
                ", preparerPersonnelNumber='" + preparerPersonnelNumber + '\'' +
                ", defaultBusinessJustificationCode='" + defaultBusinessJustificationCode + '\'' +
                ", requisitionStatus='" + requisitionStatus + '\'' +
                ", projectBuyingLegalEntityId='" + projectBuyingLegalEntityId + '\'' +
                ", isPurchaseRequisitionOnHold='" + isPurchaseRequisitionOnHold + '\'' +
                ", defaultProjectId='" + defaultProjectId + '\'' +
                ", onHoldExplanation='" + onHoldExplanation + '\'' +
                '}';
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public String getDefaultAccountingDate() {
        return defaultAccountingDate;
    }

    public String getRequisitionPurpose() {
        return requisitionPurpose;
    }

    public String getDefaultBusinessJustificationDetails() {
        return defaultBusinessJustificationDetails;
    }

    public String getDefaultRequestedDate() {
        return defaultRequestedDate;
    }

    public String getRequisitionName() {
        return requisitionName;
    }

    public String getPreparerPersonnelNumber() {
        return preparerPersonnelNumber;
    }

    public String getDefaultBusinessJustificationCode() {
        return defaultBusinessJustificationCode;
    }

    public String getRequisitionStatus() {
        return requisitionStatus;
    }

    public String getProjectBuyingLegalEntityId() {
        return projectBuyingLegalEntityId;
    }

    public String getIsPurchaseRequisitionOnHold() {
        return isPurchaseRequisitionOnHold;
    }

    public String getDefaultProjectId() {
        return defaultProjectId;
    }

    public String getOnHoldExplanation() {
        return onHoldExplanation;
    }
}