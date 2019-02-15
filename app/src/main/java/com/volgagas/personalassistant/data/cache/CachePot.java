package com.volgagas.personalassistant.data.cache;

import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:39, 06/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * Small data save-container
 */
public class CachePot {
    private static volatile CachePot cachePot;

    //cache barcode list for pass from activity to nomenclature base list. After it we send data to
    // server and update our nomenclatures, after successful result we clear this cache
    private List<Object> cacheBarcodeList;

    //barcode for pass from dialog fragment to list above QR code reader - ActivityBarcode
    private Barcode barcode;

    //saver container for reflect histories data in WorkerHistoryFragment. We get it from worker
    // today and need to save it here
    private List<TaskHistory> taskHistories;

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

    public void putTaskHistories(List<TaskHistory> histories) {
        taskHistories = new ArrayList<>(histories);
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

    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public List<Object> getCacheBarcodeList() {
        return cacheBarcodeList;
    }

    public void clearBarcode() {
        barcode = null;
    }

    public void clearCacheBarcodeList() {
        cacheBarcodeList = null;
    }

    public void clearTaskHistories() {
        taskHistories = null;
    }
}
