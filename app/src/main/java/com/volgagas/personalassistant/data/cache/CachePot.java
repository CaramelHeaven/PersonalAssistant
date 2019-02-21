package com.volgagas.personalassistant.data.cache;

import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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

    //We save apk in 'cache' for get in the future one of fields from it
    private Apk apk;

    private ResponseBody bodyApk; // apk data for save into SaveApkWorker

    private List<Nomenclature> createList; // create new nomenclatures
    private List<Nomenclature> updateList; // update nomenclatures

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

    public void putCreateNomenclatures(List<Nomenclature> data) {
        createList = new ArrayList<>(data);
    }

    public void putUpdateNomenclatures(List<Nomenclature> data) {
        updateList = new ArrayList<>(data);
    }

    public List<Nomenclature> getCreateList() {
        return createList;
    }

    public List<Nomenclature> getUpdateList() {
        return updateList;
    }

    public void putBarcodeCache(Barcode data) {
        barcode = new Barcode();

        barcode.setBarcodeName(data.getBarcodeName());
        barcode.setBarcode(data.getBarcode());
        barcode.setCount(data.getCount());
        barcode.setUnit(data.getUnit());
        barcode.setItemBarCode(data.getItemBarCode());
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

    public void saveApk(Apk data) {
        apk = new Apk(data.getName(), data.getUrlReference());
    }

    public void clearApk() {
        apk = null;
    }

    public void clearBodyApk() {
        bodyApk = null;
    }

    public ResponseBody getBodyApk() {
        return bodyApk;
    }

    public void setBodyApk(ResponseBody bodyApk) {
        this.bodyApk = bodyApk;
    }

    public Apk getApk() {
        return apk;
    }

    public void setApk(Apk apk) {
        this.apk = apk;
    }
}
