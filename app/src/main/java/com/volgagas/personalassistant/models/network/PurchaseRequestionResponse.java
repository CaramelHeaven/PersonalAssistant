package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.orders.PurchaseRequisitionNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:39, 27/02/2019.
 */
public class PurchaseRequestionResponse {
    @SerializedName("value")
    @Expose
    private List<PurchaseRequisitionNetwork> value = null;

    @Override
    public String toString() {
        return "PurchaseRequestionResponse{" +
                "value=" + value +
                '}';
    }

    public List<PurchaseRequisitionNetwork> getValue() {
        return value;
    }
}
