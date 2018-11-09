package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 16:44, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Author {
    @SerializedName("__metadata")
    @Expose
    private Metadata_ metadata;
    @SerializedName("Title")
    @Expose
    private String title;

    public Metadata_ getMetadata() {
        return metadata;
    }

    public String getTitle() {
        return title;
    }

}
