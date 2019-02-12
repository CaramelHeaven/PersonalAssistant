package com.volgagas.personalassistant.presentation.contracts.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface ContractView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showContracts(List<T> values);
}
