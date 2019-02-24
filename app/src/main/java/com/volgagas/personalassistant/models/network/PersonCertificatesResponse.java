package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.info.PersonCertificatesNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:47, 24/02/2019.
 */
public class PersonCertificatesResponse {
    @SerializedName("value")
    @Expose
    private List<PersonCertificatesNetwork> value;

    public List<PersonCertificatesNetwork> getValue() {
        return value;
    }
}
