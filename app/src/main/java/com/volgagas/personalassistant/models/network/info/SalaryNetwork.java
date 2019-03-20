package com.volgagas.personalassistant.models.network.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 11:18, 26/02/2019.
 */
public class SalaryNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("PayStatementNumber")
    @Expose
    private String payStatementNumber;
    @SerializedName("PayCycleId")
    @Expose
    private String payCycleId;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("CreationType")
    @Expose
    private String creationType;
    @SerializedName("PaymentRunType")
    @Expose
    private String paymentRunType;
    @SerializedName("PeriodEndDate")
    @Expose
    private String periodEndDate;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("PayStatementStatus")
    @Expose
    private String payStatementStatus;
    @SerializedName("PayStatementReversed")
    @Expose
    private String payStatementReversed;
    @SerializedName("NetPay")
    @Expose
    private Double netPay;
    @SerializedName("PersonnelNumber")
    @Expose
    private String personnelNumber;
    @SerializedName("BatchNumber")
    @Expose
    private String batchNumber;
    @SerializedName("ReversedPayStatementNumber")
    @Expose
    private String reversedPayStatementNumber;
    @SerializedName("PeriodStartDate")
    @Expose
    private String periodStartDate;
    @SerializedName("GrossPay")
    @Expose
    private Double grossPay;
    @SerializedName("DisbursementFormat")
    @Expose
    private String disbursementFormat;
    @SerializedName("DisableAccounting")
    @Expose
    private String disableAccounting;
    @SerializedName("Posted")
    @Expose
    private String posted;
    @SerializedName("PaymentJournal")
    @Expose
    private String paymentJournal;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getPayStatementNumber() {
        return payStatementNumber;
    }

    public String getPayCycleId() {
        return payCycleId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getCreationType() {
        return creationType;
    }

    public String getPaymentRunType() {
        return paymentRunType;
    }

    public String getPeriodEndDate() {
        return periodEndDate;
    }

    public String getCompany() {
        return company;
    }

    public String getPayStatementStatus() {
        return payStatementStatus;
    }

    public String getPayStatementReversed() {
        return payStatementReversed;
    }

    public Double getNetPay() {
        return netPay;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getReversedPayStatementNumber() {
        return reversedPayStatementNumber;
    }

    public String getPeriodStartDate() {
        return periodStartDate;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public String getDisbursementFormat() {
        return disbursementFormat;
    }

    public String getDisableAccounting() {
        return disableAccounting;
    }

    public String getPosted() {
        return posted;
    }

    public String getPaymentJournal() {
        return paymentJournal;
    }
}
