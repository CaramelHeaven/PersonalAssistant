package com.volgagas.personalassistant.models.network.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 16:43, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryResult {
    @SerializedName("__metadata")
    @Expose
    private Metadata metadata;
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

    public Metadata getMetadata() {
        return metadata;
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
}