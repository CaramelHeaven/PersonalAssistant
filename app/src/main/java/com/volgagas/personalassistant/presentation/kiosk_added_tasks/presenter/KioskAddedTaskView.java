package com.volgagas.personalassistant.presentation.kiosk_added_tasks.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:12, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface KioskAddedTaskView<T> extends BaseView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void addedTaskToList(T model);
}
