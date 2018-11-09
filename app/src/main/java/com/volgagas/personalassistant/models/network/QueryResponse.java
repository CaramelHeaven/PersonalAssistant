package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.query.QueryNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:20, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryResponse {

    @SerializedName("d")
    @Expose
    private QueryNetwork queryNetwork;

    public QueryNetwork getQueryNetwork() {
        return queryNetwork;
    }
}
