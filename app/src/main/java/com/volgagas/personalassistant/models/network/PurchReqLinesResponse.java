package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.orders.PurchReqLinesNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:42, 27/02/2019.
 */
public class PurchReqLinesResponse {
    @SerializedName("value")
    @Expose
    private List<PurchReqLinesNetwork> value = null;
}
