package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.all_queries_template.QueriesNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 10:55, 17/12/2018.
 */
public class QueriesTemplateResponse {
    @SerializedName("value")
    @Expose
    private List<QueriesNetwork> queriesNetworkList;

    public List<QueriesNetwork> getQueriesNetworkList() {
        return queriesNetworkList;
    }
}
