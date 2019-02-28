package com.volgagas.personalassistant.presentation.order_purchase_requestion_more.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:47, 27/02/2019.
 */
public interface PurchaseReqiestionMoreView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItem(List<UserSubOrder> userSubOrder);
}
