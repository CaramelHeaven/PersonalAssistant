package com.volgagas.personalassistant.presentation.projects_query_from_user.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:44, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface QueryFromUserView<T> extends BaseView {
    void showItems(List<T> items);

    void showUpdatedData(List<T> data);
}
