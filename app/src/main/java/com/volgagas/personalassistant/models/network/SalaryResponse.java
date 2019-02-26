package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.info.SalaryNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:17, 26/02/2019.
 */
public class SalaryResponse {
    @SerializedName("value")
    @Expose
    private List<SalaryNetwork> value = null;

    public List<SalaryNetwork> getValue() {
        return value;
    }
}
