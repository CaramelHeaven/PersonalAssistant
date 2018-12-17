package com.volgagas.personalassistant.presentation.queries.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

public interface QueryView<T1 extends QueryToUser, T2 extends UniformRequest> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showValues(List<T1> queriesToUser, List<T2> uniformRequests);
}
