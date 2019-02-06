package com.volgagas.personalassistant.data.cache;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CaramelHeaven on 12:39, 06/02/2019.
 * Used for pass picture from barcode activity to barcode fragment
 */
public class CachePot {
    private static volatile CachePot cachePot;
    private Map<String, Object> cacheBitmap;

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

    public Bitmap getCacheBitmapByScannedString(String outResult) {
        return (Bitmap) cacheBitmap.get(outResult);
    }

    public void clearCache() {
        cacheBitmap.clear();
    }
}
