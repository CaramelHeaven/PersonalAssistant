package com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Created by CaramelHeaven on 11:31, 31/01/2019.
 */
public interface NomenclatureDFView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showBarcodeResult(T t);
}
