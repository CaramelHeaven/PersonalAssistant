package com.volgagas.personalassistant.presentation.order_new_purchase.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

/**
 * Created by CaramelHeaven on 12:40, 25/12/2018.
 */
@InjectViewState
public class OrderNewPurchasePresenter extends BasePresenter<OrderNewPurchaseView<Order>> {

    public OrderNewPurchasePresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }
}
