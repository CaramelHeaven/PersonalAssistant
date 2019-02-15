package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("codekeyList")
    @Expose
    private List<String> codekeyList;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("lastEntered")
    @Expose
    private String lastEntered;
    @SerializedName("category")
    private String category;
    @SerializedName("previewPhoto")
    private String userImage;

    public String getName() {
        return name;
    }

    public String getJob() {
        return position;
    }

    public List<String> getCodekeyList() {
        return codekeyList;
    }

    public String getPosition() {
        return position;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLastEntered() {
        return lastEntered;
    }

    public String getCategory() {
        return category;
    }

    public String getUserImage() {
        return userImage;
    }
}
