package com.volgagas.personalassistant.models.network.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 16:39, 18/03/2019.
 */
public class DimensionMappingNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("ProjCategoryEmplId")
    @Expose
    private String projCategoryEmplId;
    @SerializedName("ProjCategoryItemId")
    @Expose
    private String projCategoryItemId;
    @SerializedName("InventDimId")
    @Expose
    private String inventDimId;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getProjCategoryEmplId() {
        return projCategoryEmplId;
    }

    public String getProjCategoryItemId() {
        return projCategoryItemId;
    }

    public String getInventDimId() {
        return inventDimId;
    }
}
