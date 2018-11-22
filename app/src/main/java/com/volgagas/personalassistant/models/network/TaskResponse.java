package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:43, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskResponse {
    @SerializedName("value")
    @Expose
    private List<TaskNetwork> value = null;

    public List<TaskNetwork> getValue() {
        return value;
    }
}
