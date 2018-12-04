package com.volgagas.personalassistant.presentation.worker_today.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

public interface WorkerTodayView<T extends Task> extends BaseView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<T> models);
}
