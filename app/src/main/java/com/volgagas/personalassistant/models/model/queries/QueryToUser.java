package com.volgagas.personalassistant.models.model.queries;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:28, 17/12/2018.
 * From uniform requests, where queries TO User
 */
public class QueryToUser extends QueryBase {
    private String title;
    private String priority;
    private String comment;
    private String date;
    private String category;

    private List<String> assignedTo = null;

    @Override
    public String toString() {
        return "QueryToUser{" +
                "title='" + title + '\'' +
                ", priority='" + priority + '\'' +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", assignedTo=" + assignedTo +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
