package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BaseView;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface RecipientView extends BaseView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showUsers(List<User> values);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void sendUserData(RequestData data);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void finish();
}
