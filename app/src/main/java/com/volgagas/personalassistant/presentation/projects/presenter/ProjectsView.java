package com.volgagas.personalassistant.presentation.projects.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

/**
 * Created by CaramelHeaven on 11:46, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface ProjectsView extends BaseView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showError(Throwable throwable);
}
