package com.volgagas.personalassistant.presentation.worker_gpa.presenter;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;
/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface GpaView extends BaseView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showError(String error);

    @StateStrategyType(value = SkipStrategy.class)
    void completed();
}
