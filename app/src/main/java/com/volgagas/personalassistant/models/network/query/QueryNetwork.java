package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 14:00, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryNetwork {

    @SerializedName("odata.type")
    @Expose
    private String odataType;
    @SerializedName("odata.id")
    @Expose
    private String odataId;
    @SerializedName("odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("odata.editLink")
    @Expose
    private String odataEditLink;
    @SerializedName("Author@odata.navigationLinkUrl")
    @Expose
    private String authorOdataNavigationLinkUrl;
    @SerializedName("Author")
    @Expose
    private Author author;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("LastText")
    @Expose
    private Object lastText;

    public String getOdataType() {
        return odataType;
    }

    public String getOdataId() {
        return odataId;
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getOdataEditLink() {
        return odataEditLink;
    }

    public String getAuthorOdataNavigationLinkUrl() {
        return authorOdataNavigationLinkUrl;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPriority() {
        return priority;
    }

    public String getComment() {
        return comment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Object getLastText() {
        return lastText;
    }
}
