package com.volgagas.personalassistant.presentation.projects_query_to_user.presenter;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:45, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface QueryToUserView<T extends QueryToUser> extends BaseView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showItems(List<T> items);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showUpdatedData(List<T> data);
}
