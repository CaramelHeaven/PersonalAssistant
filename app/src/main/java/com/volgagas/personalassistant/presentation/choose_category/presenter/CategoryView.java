package com.volgagas.personalassistant.presentation.choose_category.presenter;

import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:24, 14.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface CategoryView<T> extends BaseView {
    void showItems(List<T> items);
}
