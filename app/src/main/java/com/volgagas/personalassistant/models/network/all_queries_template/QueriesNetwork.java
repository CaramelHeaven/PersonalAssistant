package com.volgagas.personalassistant.models.network.all_queries_template;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaramelHeaven on 10:56, 17/12/2018.
 */
public class QueriesNetwork {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Title")
    @Expose
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
