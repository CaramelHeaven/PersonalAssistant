package com.volgagas.personalassistant.presentation.worker_result.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface ResultView extends BaseView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showSendStatus();

    @StateStrategyType(value = SingleStateStrategy.class)
    void completed();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void callbackFromResultDialog(boolean bool);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void timeout();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showError(String error);
}
