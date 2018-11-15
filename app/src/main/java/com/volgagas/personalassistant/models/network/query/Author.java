package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 16:44, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Author {

    @SerializedName("odata.type")
    @Expose
    private String odataType;
    @SerializedName("odata.id")
    @Expose
    private String odataId;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    public String getOdataId() {
        return odataId;
    }

    public void setOdataId(String odataId) {
        this.odataId = odataId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}