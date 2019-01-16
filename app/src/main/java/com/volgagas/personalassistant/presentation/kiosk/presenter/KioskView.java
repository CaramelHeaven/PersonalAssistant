package com.volgagas.personalassistant.presentation.kiosk.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Created by CaramelHeaven on 16:53, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface KioskView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void sendTemplatesProgress();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void completedKiosk();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void handlerError();
}
