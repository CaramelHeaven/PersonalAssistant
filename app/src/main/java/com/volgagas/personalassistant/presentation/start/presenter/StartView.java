package com.volgagas.personalassistant.presentation.start.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface StartView extends BaseView {

    void resultMatchedWithEquipment();

    void commonError(Throwable throwable);

    void goToMainMenu();

    void showErrorToEnter();

    void enableNFC();
}
