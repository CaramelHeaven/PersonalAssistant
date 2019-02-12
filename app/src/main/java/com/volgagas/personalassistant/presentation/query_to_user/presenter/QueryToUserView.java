package com.volgagas.personalassistant.presentation.query_to_user.presenter;

import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:45, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface QueryToUserView<T extends QueryToUser> extends BaseView {
    void showItems(List<T> items);
}
