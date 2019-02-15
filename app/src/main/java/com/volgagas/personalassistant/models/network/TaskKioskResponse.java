package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.task_kiosk.TaskKioskNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:03, 27/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskKioskResponse {
    @SerializedName("value")
    @Expose
    private List<TaskKioskNetwork> value = null;

    public List<TaskKioskNetwork> getValue() {
        return value;
    }
}
