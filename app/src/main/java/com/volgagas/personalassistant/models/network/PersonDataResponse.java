package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.info.PersonDataNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:42, 25/02/2019.
 */
public class PersonDataResponse {
    @SerializedName("value")
    @Expose
    private List<PersonDataNetwork> value = null;

    public List<PersonDataNetwork> getValue() {
        return value;
    }
}
