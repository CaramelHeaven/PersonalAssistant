package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.task.DimensionMappingNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:38, 18/03/2019.
 */
public class DimensionMappingResponse {
    @SerializedName("value")
    @Expose
    private List<DimensionMappingNetwork> value = null;

    public List<DimensionMappingNetwork> getValue() {
        return value;
    }
}
