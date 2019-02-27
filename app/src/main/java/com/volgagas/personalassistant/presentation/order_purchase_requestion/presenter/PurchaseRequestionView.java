package com.volgagas.personalassistant.presentation.order_purchase_requestion.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:17, 27/02/2019.
 */
public interface PurchaseRequestionView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<UserOrder> orders);
}
