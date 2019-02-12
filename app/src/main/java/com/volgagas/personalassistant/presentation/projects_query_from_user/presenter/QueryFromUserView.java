package com.volgagas.personalassistant.presentation.projects_query_from_user.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:44, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface QueryFromUserView<T> extends BaseView {
    void showItems(List<T> items);
}
