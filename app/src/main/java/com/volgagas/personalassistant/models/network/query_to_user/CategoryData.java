package com.volgagas.personalassistant.models.network.query_to_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 10:30, 25/12/2018.
 */
public class CategoryData {
    @SerializedName("Title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }
}
