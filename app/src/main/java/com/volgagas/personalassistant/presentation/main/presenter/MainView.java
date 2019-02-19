package com.volgagas.personalassistant.presentation.main.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface MainView extends MvpView {
    void createProgressNotification();

    void showError(Throwable throwable);
}
