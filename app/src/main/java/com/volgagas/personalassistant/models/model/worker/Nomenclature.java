package com.volgagas.personalassistant.models.model.worker;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 12:15, 16/01/2019.
 */
public class Nomenclature {
    private String name;
    private int count;
    private String unit;
    private String projectCategoryId; // field for patch or post nomenclature to server

    public Nomenclature(String name, int count, String unit) {
        this.name = name;
        this.count = count;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getUnit() {
        return unit;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(String projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    @Override
    public String toString() {
        return "Nomenclature{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                ", projectCategoryId='" + projectCategoryId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nomenclature that = (Nomenclature) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
