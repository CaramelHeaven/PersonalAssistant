package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 09:59, 28/01/2019.
 */
public class NomenclatureHostResponse {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
