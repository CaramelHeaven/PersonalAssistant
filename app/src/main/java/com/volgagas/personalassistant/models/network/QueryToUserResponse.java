package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserAssignedTo;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:22, 17/12/2018.
 */
public class QueryToUserResponse {

    @SerializedName("value")
    @Expose
    private List<QueryToUserNetwork> value = null;

    public List<QueryToUserNetwork> getValue() {
        return value;
    }
}
