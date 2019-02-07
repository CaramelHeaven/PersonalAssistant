package com.volgagas.personalassistant.presentation.about_user.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

public interface InfoView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showData(List<Object> objects);
}
