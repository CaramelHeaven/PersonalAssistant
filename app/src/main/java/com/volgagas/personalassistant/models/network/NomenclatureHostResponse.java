package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 09:59, 28/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class NomenclatureHostResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getUnit() {
        return unit;
    }

    public String getQuantity() {
        return quantity;
    }
}
