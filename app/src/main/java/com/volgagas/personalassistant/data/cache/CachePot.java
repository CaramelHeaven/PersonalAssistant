package com.volgagas.personalassistant.data.cache;

import android.graphics.Bitmap;

import com.volgagas.personalassistant.models.model.worker.Barcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CaramelHeaven on 12:39, 06/02/2019.
 * Small data save-container
 */
public class CachePot {
    private static volatile CachePot cachePot;

    //cache barcode list for pass from activity to nomenclature base list
    private List<Object> cacheBarcodeList;

    //barcode for pass from dialog fragment to list above QR code reader - ActivityBarcode
    private Barcode barcode;

    public static CachePot getInstance() {
        if (cachePot == null) {
            synchronized (CachePot.class) {
                if (cachePot == null) {
                    cachePot = new CachePot();
                }
            }
        }

        return cachePot;
    }

    public void putBarcodeCacheList(List<Object> objects) {
        cacheBarcodeList = new ArrayList<>();

        cacheBarcodeList.addAll(objects);
    }

    public void putBarcodeCache(Barcode data) {
        barcode = new Barcode();

        barcode.setBarcodeName(data.getBarcodeName());
        barcode.setBarcode(data.getBarcode());
        barcode.setCount(data.getCount());
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void clearBarcode() {
        barcode = null;
    }

    public void clearCacheBarcodeList() {
        cacheBarcodeList = null;
    }

    public List<Object> getCacheBarcodeList() {
        return cacheBarcodeList;
    }
}
