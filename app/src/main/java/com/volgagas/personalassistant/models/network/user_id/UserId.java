package com.volgagas.personalassistant.models.network.user_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 12:31, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserId {
    @SerializedName("Id")
    @Expose
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
