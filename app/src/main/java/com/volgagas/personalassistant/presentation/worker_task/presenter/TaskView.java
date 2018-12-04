package com.volgagas.personalassistant.presentation.worker_task.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 08:53, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface TaskView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<T> subTasks);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showError();
}
