package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.orders.PurchaseOrderNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 19:07, 27/02/2019.
 */
public class PurchaseOrderResponse {
    @SerializedName("value")
    @Expose
    private List<PurchaseOrderNetwork> value = null;

    public List<PurchaseOrderNetwork> getValue() {
        return value;
    }
}
