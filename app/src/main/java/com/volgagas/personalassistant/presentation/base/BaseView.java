package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BaseView extends MvpView {
    void showProgress();

    void hideProgress();

    /**
     * Means that something from server comes with error which we can not handler
     * <p>
     * When I developed this code (shit) I dont know about errors from server. I make it from user feedback
     */
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void catastrophicError(Throwable throwable);
}
