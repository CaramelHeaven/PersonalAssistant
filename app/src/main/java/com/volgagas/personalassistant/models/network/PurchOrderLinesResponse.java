package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.orders.PurchOrderLinesNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 19:18, 27/02/2019.
 */
public class PurchOrderLinesResponse {
    @SerializedName("value")
    @Expose
    private List<PurchOrderLinesNetwork> value = null;

    public List<PurchOrderLinesNetwork> getValue() {
        return value;
    }
}
