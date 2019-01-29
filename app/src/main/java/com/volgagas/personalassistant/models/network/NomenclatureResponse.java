package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.nomenclature.NomenclatureNetwork;

import java.util.Arrays;
import java.util.List;

/**
 * Created by CaramelHeaven on 12:16, 16/01/2019.
 */
public class NomenclatureResponse {
    @SerializedName("value")
    @Expose
    private List<NomenclatureNetwork> value = null;

    public List<NomenclatureNetwork> getValue() {
        return value;
    }
}