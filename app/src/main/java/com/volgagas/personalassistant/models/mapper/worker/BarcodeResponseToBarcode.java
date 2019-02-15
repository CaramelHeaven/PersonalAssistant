package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.network.BarcodeResponse;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:47, 31/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class BarcodeResponseToBarcode extends Mapper<BarcodeResponse, Barcode> {
    @Override
    public Barcode map(BarcodeResponse value) {
        Barcode barcode = new Barcode();
        fillData(barcode, value);

        return barcode;
    }

    @Override
    protected void fillData(Barcode barcode, BarcodeResponse barcodeResponse) {
        barcode.setBarcodeName(barcodeResponse.getItemId());
        barcode.setBarcode(barcodeResponse.getItemBarCode());
        barcode.setUnit(barcodeResponse.getUnitID());
        barcode.setCount(barcodeResponse.getQty());
    }
}
