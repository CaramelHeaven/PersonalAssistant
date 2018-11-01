package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("codekey")
    @Expose
    private String codekey;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("lastEntered")
    @Expose
    private String lastEntered;
    @SerializedName("category")
    private String category;

    public String getName() {
        return name;
    }

    public String getJob() {
        return position;
    }

    public String getCodekey() {
        return codekey;
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
}
