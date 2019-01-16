package com.volgagas.personalassistant.presentation.worker_result.presenter;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

public interface ResultView extends BaseView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showSendStatus();

    @StateStrategyType(value = SingleStateStrategy.class)
    void completed();
}
