package com.volgagas.personalassistant.presentation.main.presenter;

import android.arch.lifecycle.OnLifecycleEvent;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import okhttp3.ResponseBody;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface MainView extends MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void saveFileFromServer(ResponseBody body);
}
