package com.volgagas.personalassistant.models.network.sub_task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubTaskNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("SO_ServiceOrder")
    @Expose
    private String sOServiceOrder;
    @SerializedName("AC_ActivityId")
    @Expose
    private String aCActivityId;
    @SerializedName("AC_ActivityEndDateTime")
    @Expose
    private String aCActivityEndDateTime;
    @SerializedName("SO_PreferredServiceTime")
    @Expose
    private String sOPreferredServiceTime;
    @SerializedName("SO_Description")
    @Expose
    private String sODescription;
    @SerializedName("SO_ServiceStage")
    @Expose
    private String sOServiceStage;
    @SerializedName("AC_Worker")
    @Expose
    private String aCWorker;
    @SerializedName("AC_Description")
    @Expose
    private String aCDescription;
    @SerializedName("AC_ActivityStartDateTime")
    @Expose
    private String aCActivityStartDateTime;
    @SerializedName("AC_ServiceObject")
    @Expose
    private String aCServiceObject;
    @SerializedName("AC_ServiceOrderLineNum")
    @Expose
    private Integer aCServiceOrderLineNum;
    @SerializedName("AC_Closed")
    @Expose
    private String state;

    @Override
    public String toString() {
        return "SubTaskNetwork{" +
                "odataEtag='" + odataEtag + '\'' +
                ", dataAreaId='" + dataAreaId + '\'' +
                ", sOServiceOrder='" + sOServiceOrder + '\'' +
                ", aCActivityId='" + aCActivityId + '\'' +
                ", aCActivityEndDateTime='" + aCActivityEndDateTime + '\'' +
                ", sOPreferredServiceTime='" + sOPreferredServiceTime + '\'' +
                ", sODescription='" + sODescription + '\'' +
                ", sOServiceStage='" + sOServiceStage + '\'' +
                ", aCWorker='" + aCWorker + '\'' +
                ", aCDescription='" + aCDescription + '\'' +
                ", aCActivityStartDateTime='" + aCActivityStartDateTime + '\'' +
                ", aCServiceObject='" + aCServiceObject + '\'' +
                ", aCServiceOrderLineNum=" + aCServiceOrderLineNum +
                ", state='" + state + '\'' +
                '}';
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getsOServiceOrder() {
        return sOServiceOrder;
    }

    public String getaCActivityId() {
        return aCActivityId;
    }

    public String getaCActivityEndDateTime() {
        return aCActivityEndDateTime;
    }

    public String getsOPreferredServiceTime() {
        return sOPreferredServiceTime;
    }

    public String getsODescription() {
        return sODescription;
    }

    public String getsOServiceStage() {
        return sOServiceStage;
    }

    public String getaCWorker() {
        return aCWorker;
    }

    public String getaCDescription() {
        return aCDescription;
    }

    public String getaCActivityStartDateTime() {
        return aCActivityStartDateTime;
    }

    public String getaCServiceObject() {
        return aCServiceObject;
    }

    public Integer getaCServiceOrderLineNum() {
        return aCServiceOrderLineNum;
    }

    public String getState() {
        return state;
    }
}
