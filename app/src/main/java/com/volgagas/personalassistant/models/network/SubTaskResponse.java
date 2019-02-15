package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.sub_task.SubTaskNetwork;

import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class SubTaskResponse {
    @SerializedName("value")
    @Expose
    private List<SubTaskNetwork> value = null;

    public List<SubTaskNetwork> getValue() {
        return value;
    }
}