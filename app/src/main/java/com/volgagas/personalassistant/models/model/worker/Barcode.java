package com.volgagas.personalassistant.models.model.worker;

import java.util.Objects;

/**
 * Created by CaramelHeaven on 10:54, 31/01/2019.
 */
public class Barcode {
    private String barcodeName;
    private String barcode;
    private int count;
    private String unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barcode barcode1 = (Barcode) o;
        return Objects.equals(barcodeName, barcode1.barcodeName) &&
                Objects.equals(barcode, barcode1.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcodeName, barcode);
    }

    @Override
    public String toString() {
        return "Barcode{" +
                "barcodeName='" + barcodeName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                '}';
    }

    public String getBarcodeName() {
        return barcodeName;
    }

    public void setBarcodeName(String barcodeName) {
        this.barcodeName = barcodeName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
