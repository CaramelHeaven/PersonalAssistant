package com.volgagas.personalassistant.models.model.worker;

/**
 * Created by CaramelHeaven on 12:15, 16/01/2019.
 */
public class Nomenclature {
    private String name;
    private int count;
    private String unit;

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
}
