package com.volgagas.personalassistant.models.network.query_to_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:24, 17/12/2018.
 */
public class QueryToUserNetwork {
    @SerializedName("AssignedTo")
    @Expose
    private List<QueryToUserAssignedTo> assignedTo = null;
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

    public List<QueryToUserAssignedTo> getAssignedTo() {
        return assignedTo;
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
