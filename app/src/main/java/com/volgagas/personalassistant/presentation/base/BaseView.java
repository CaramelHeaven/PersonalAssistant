package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {
    void showProgress();

    void hideProgress();
}
