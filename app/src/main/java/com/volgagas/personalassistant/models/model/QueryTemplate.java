package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 11:18, 17/12/2018.
 */
public class QueryTemplate {
    private String title;

    public QueryTemplate(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "QueryTemplate{" +
                "title='" + title + '\'' +
                '}';
    }
}
