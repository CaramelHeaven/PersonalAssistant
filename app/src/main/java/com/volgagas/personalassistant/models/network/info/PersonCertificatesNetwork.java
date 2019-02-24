package com.volgagas.personalassistant.models.network.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 13:48, 24/02/2019.
 */
public class PersonCertificatesNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("PartyNumber")
    @Expose
    private String partyNumber;
    @SerializedName("CertificateTypeId")
    @Expose
    private String certificateTypeId;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("Notes")
    @Expose
    private String notes;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public String getCertificateTypeId() {
        return certificateTypeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNotes() {
        return notes;
    }
}
