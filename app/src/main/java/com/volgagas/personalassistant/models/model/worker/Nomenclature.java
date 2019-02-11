package com.volgagas.personalassistant.models.model.worker;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Nomenclature{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nomenclature that = (Nomenclature) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, unit);
    }
}
