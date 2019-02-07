package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.worker.Barcode;

/**
 * Created by CaramelHeaven on 15:40, 06/02/2019.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface BarcodeListView extends MvpView {
    void stateOfLayout(Boolean bool);

    void updateItem(Barcode barcode);

    void grabData();
}
