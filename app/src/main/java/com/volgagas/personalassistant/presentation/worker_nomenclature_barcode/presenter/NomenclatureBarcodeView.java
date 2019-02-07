package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.worker.Barcode;

/**
 * Created by CaramelHeaven on 12:10, 31/01/2019.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface NomenclatureBarcodeView extends MvpView {
    void resumeBarcode();

    void getDataAndComlpeted();
}
