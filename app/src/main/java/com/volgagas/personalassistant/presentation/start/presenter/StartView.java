package com.volgagas.personalassistant.presentation.start.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

public interface StartView extends BaseView {

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void requestD365Token();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void resultMatchedWithEquipment();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void commonError();
}
