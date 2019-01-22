package com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Created by CaramelHeaven on 12:03, 22/01/2019.
 */
public interface ChooseActivityView extends BaseView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void enableNFC();
}
