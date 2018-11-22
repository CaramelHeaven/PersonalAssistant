package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.user_dynamics.UserDynamicsNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:48, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserDynamicsResponse {
    @SerializedName("value")
    @Expose
    private List<UserDynamicsNetwork> value = null;

    public List<UserDynamicsNetwork> getValue() {
        return value;
    }

    public void setValue(List<UserDynamicsNetwork> value) {
        this.value = value;
    }
}
