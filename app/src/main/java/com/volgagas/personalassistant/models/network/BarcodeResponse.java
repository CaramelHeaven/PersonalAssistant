package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 11:48, 31/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class BarcodeResponse {
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("itemBarCode")
    @Expose
    private String itemBarCode;
    @SerializedName("barcodeSetupId")
    @Expose
    private String barcodeSetupId;
    @SerializedName("UnitID")
    @Expose
    private String unitID;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("useForInput")
    @Expose
    private String useForInput;
    @SerializedName("RetailShowForItem")
    @Expose
    private String retailShowForItem;
    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("useForPrinting")
    @Expose
    private String useForPrinting;
    @SerializedName("inventDimId")
    @Expose
    private String inventDimId;
    @SerializedName("Blocked")
    @Expose
    private String blocked;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("RetailVariantId")
    @Expose
    private String retailVariantId;

    @Override
    public String toString() {
        return "BarcodeResponse{" +
                "odataContext='" + odataContext + '\'' +
                ", odataEtag='" + odataEtag + '\'' +
                ", dataAreaId='" + dataAreaId + '\'' +
                ", itemBarCode='" + itemBarCode + '\'' +
                ", barcodeSetupId='" + barcodeSetupId + '\'' +
                ", unitID='" + unitID + '\'' +
                ", description='" + description + '\'' +
                ", useForInput='" + useForInput + '\'' +
                ", retailShowForItem='" + retailShowForItem + '\'' +
                ", itemId='" + itemId + '\'' +
                ", useForPrinting='" + useForPrinting + '\'' +
                ", inventDimId='" + inventDimId + '\'' +
                ", blocked='" + blocked + '\'' +
                ", qty=" + qty +
                ", retailVariantId='" + retailVariantId + '\'' +
                '}';
    }

    public String getOdataContext() {
        return odataContext;
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getItemBarCode() {
        return itemBarCode;
    }

    public String getBarcodeSetupId() {
        return barcodeSetupId;
    }

    public String getUnitID() {
        return unitID;
    }

    public String getDescription() {
        return description;
    }

    public String getUseForInput() {
        return useForInput;
    }

    public String getRetailShowForItem() {
        return retailShowForItem;
    }

    public String getItemId() {
        return itemId;
    }

    public String getUseForPrinting() {
        return useForPrinting;
    }

    public String getInventDimId() {
        return inventDimId;
    }

    public String getBlocked() {
        return blocked;
    }

    public Integer getQty() {
        return qty;
    }

    public String getRetailVariantId() {
        return retailVariantId;
    }
}
