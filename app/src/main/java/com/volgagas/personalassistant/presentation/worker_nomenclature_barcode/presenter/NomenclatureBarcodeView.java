package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.worker.Barcode;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface NomenclatureBarcodeView extends MvpView {
    void resumeBarcode();

    void getDataAndComlpeted();

    void successfulGetBarcodeFromServer(Barcode barcode);

    void showProgressDialog();

    void hideProgressDialog();

    void showNotFoundNomenclature();
}
