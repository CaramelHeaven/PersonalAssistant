package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface RecipientView extends BaseView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showUsers(List<User> values);
}
