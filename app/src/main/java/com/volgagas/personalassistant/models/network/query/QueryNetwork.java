package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:41, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryNetwork {
    @SerializedName("results")
    @Expose
    private List<QueryResult> results = null;

    public List<QueryResult> getResults() {
        return results;
    }
}
