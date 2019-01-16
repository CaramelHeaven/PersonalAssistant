package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:16, 16/01/2019.
 */
public class NomenclatureResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("codekeyList")
    @Expose
    private List<String> codekeyList;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getCodekeyList() {
        return codekeyList;
    }
}
