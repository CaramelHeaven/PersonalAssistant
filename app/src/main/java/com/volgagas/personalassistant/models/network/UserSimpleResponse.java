package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 17:14, 09/01/2019.
 */
public class UserSimpleResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("previewPhoto")
    @Expose
    private String previewPhoto;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreviewPhoto() {
        return previewPhoto;
    }
}
