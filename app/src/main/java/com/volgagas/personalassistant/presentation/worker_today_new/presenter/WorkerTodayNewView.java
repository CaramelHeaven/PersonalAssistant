package com.volgagas.personalassistant.presentation.worker_today_new.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:41, 23/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface WorkerTodayNewView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<T> models);
}
