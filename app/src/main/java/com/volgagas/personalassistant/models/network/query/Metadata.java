package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 16:43, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Metadata {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getEtag() {
        return etag;
    }

    public String getType() {
        return type;
    }
}
