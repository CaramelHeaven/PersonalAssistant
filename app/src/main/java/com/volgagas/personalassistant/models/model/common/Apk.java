package com.volgagas.personalassistant.models.model.common;

/**
 * Created by CaramelHeaven on 12:33, 18/02/2019.
 */
public class Apk {
    private String name;
    private String urlReference;

    public Apk(String name, String urlReference) {
        this.name = name;
        this.urlReference = urlReference;
    }

    public Apk() {
    }

    @Override
    public String toString() {
        return "Apk{" +
                "name='" + name + '\'' +
                ", urlReference='" + urlReference + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getUrlReference() {
        return urlReference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlReference(String urlReference) {
        this.urlReference = urlReference;
    }
}
