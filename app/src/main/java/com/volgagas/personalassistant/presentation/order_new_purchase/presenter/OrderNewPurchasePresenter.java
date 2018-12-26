package com.volgagas.personalassistant.presentation.order_new_purchase.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 12:40, 25/12/2018.
 */
@InjectViewState
public class OrderNewPurchasePresenter extends BasePresenter<OrderNewPurchaseView<Order>> {

    private List<NewOrder> chosenOrders;

    public OrderNewPurchasePresenter() {
        chosenOrders = new ArrayList<>();
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

    public void addNewOrder(NewOrder newOrder) {
        chosenOrders.add(newOrder);
    }

    public void removeNewOrder(NewOrder newOrder) {
        chosenOrders.remove(newOrder);
    }

    public List<NewOrder> getChosenOrders() {
        return chosenOrders;
    }
}
