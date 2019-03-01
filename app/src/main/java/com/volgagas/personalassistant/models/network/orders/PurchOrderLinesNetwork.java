package com.volgagas.personalassistant.models.network.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 19:19, 27/02/2019.
 */
public class PurchOrderLinesNetwork {
    @SerializedName("ProcurementProductCategoryName")
    @Expose
    private String procurementProductCategoryName;
    @SerializedName("FormattedDelveryAddress")
    @Expose
    private String formattedDelveryAddress;
    @SerializedName("LineDescription")
    @Expose
    private String lineDescription;

    public String getProcurementProductCategoryName() {
        return procurementProductCategoryName;
    }

    public String getFormattedDelveryAddress() {
        return formattedDelveryAddress;
    }

    public String getLineDescription() {
        return lineDescription;
    }
}
