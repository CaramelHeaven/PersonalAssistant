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

    //CacheBitmap used for pass picture from barcode activity to barcode fragment
    private Map<String, Object> cacheBitmap;
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

    public void putCacheBitmap(String outResult, Bitmap bitmap) {
        if (cacheBitmap == null) {
            cacheBitmap = new HashMap<>();
        }

        cacheBitmap.put(outResult, bitmap);
    }

    public void putBarcodeCacheList(List<Object> objects) {
        if (cacheBarcodeList == null) {
            cacheBarcodeList = new ArrayList<>();
        }

        if (cacheBarcodeList.size() > 0) {
            cacheBarcodeList.clear();
        }

        cacheBarcodeList.addAll(objects);
    }

    public void putBarcodeCache(Barcode data) {
        if (barcode == null) {
            barcode = new Barcode();
        }

        barcode.setBarcodeName(data.getBarcodeName());
        barcode.setBarcode(data.getBarcode());
        barcode.setCount(data.getCount());
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public Bitmap getCacheBitmapByScannedString(String outResult) {
        return (Bitmap) cacheBitmap.get(outResult);
    }

    public List<Object> getCacheBarcodeList() {
        return cacheBarcodeList;
    }

    public void clearPictureCache() {
        cacheBitmap.clear();
    }
}
