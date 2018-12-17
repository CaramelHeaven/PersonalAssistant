package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 11:18, 17/12/2018.
 */
public class QueryTemplate {
    private int id;
    private String title;

    public QueryTemplate(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "QueryTemplate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
