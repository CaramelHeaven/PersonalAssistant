package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface NomenclatureView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showBaseList(List<Nomenclature> values);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void addNomenclatureToBaseList(Nomenclature value);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void errorNomenclature();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void addedBarcodeNomenclaturesToBaseList(List<Nomenclature> values);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void acceptAndCloseView();
}
