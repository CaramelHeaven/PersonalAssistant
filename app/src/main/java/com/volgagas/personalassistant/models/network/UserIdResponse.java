package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.user_id.UserId;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:27, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserIdResponse {
    @SerializedName("value")
    @Expose
    private List<UserId> value = null;

    public List<UserId> getValue() {
        return value;
    }

    public void setValue(List<UserId> value) {
        this.value = value;
    }
}
