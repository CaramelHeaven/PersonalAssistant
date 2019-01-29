package com.volgagas.personalassistant.models.network.nomenclature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 11:09, 29/01/2019.
 */
public class NomenclatureNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("ServiceOrderId")
    @Expose
    private String serviceOrderId;
    @SerializedName("ServiceOrderLineNum")
    @Expose
    private Integer serviceOrderLineNum;
    @SerializedName("ToInventDimId")
    @Expose
    private String toInventDimId;
    @SerializedName("ServiceObjectId")
    @Expose
    private String serviceObjectId;
    @SerializedName("DateRangeTo")
    @Expose
    private String dateRangeTo;
    @SerializedName("TransactionType")
    @Expose
    private String transactionType;
    @SerializedName("TransactionSubType")
    @Expose
    private String transactionSubType;
    @SerializedName("Worker")
    @Expose
    private Long worker;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("DateRangeFrom")
    @Expose
    private String dateRangeFrom;
    @SerializedName("ProjId")
    @Expose
    private String projId;
    @SerializedName("ServiceTaskId")
    @Expose
    private String serviceTaskId;
    @SerializedName("ProjCategoryId")
    @Expose
    private String projCategoryId;
    @SerializedName("ItemId")
    @Expose
    private String itemId;
    @SerializedName("Qty")
    @Expose
    private Integer qty;

    @Override
    public String toString() {
        return "NomenclatureNetwork{" +
                "odataEtag='" + odataEtag + '\'' +
                ", dataAreaId='" + dataAreaId + '\'' +
                ", serviceOrderId='" + serviceOrderId + '\'' +
                ", serviceOrderLineNum=" + serviceOrderLineNum +
                ", toInventDimId='" + toInventDimId + '\'' +
                ", serviceObjectId='" + serviceObjectId + '\'' +
                ", dateRangeTo='" + dateRangeTo + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionSubType='" + transactionSubType + '\'' +
                ", worker=" + worker +
                ", unit='" + unit + '\'' +
                ", dateRangeFrom='" + dateRangeFrom + '\'' +
                ", projId='" + projId + '\'' +
                ", serviceTaskId='" + serviceTaskId + '\'' +
                ", projCategoryId='" + projCategoryId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", qty=" + qty +
                '}';
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public Integer getServiceOrderLineNum() {
        return serviceOrderLineNum;
    }

    public String getToInventDimId() {
        return toInventDimId;
    }

    public String getServiceObjectId() {
        return serviceObjectId;
    }

    public String getDateRangeTo() {
        return dateRangeTo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionSubType() {
        return transactionSubType;
    }

    public Long getWorker() {
        return worker;
    }

    public String getUnit() {
        return unit;
    }

    public String getDateRangeFrom() {
        return dateRangeFrom;
    }

    public String getProjId() {
        return projId;
    }

    public String getServiceTaskId() {
        return serviceTaskId;
    }

    public String getProjCategoryId() {
        return projCategoryId;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQty() {
        return qty;
    }
}
