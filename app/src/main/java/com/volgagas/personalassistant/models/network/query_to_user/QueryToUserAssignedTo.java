package com.volgagas.personalassistant.models.network.query_to_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 15:24, 17/12/2018.
 */
public class QueryToUserAssignedTo {
    @SerializedName("Title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }
}
