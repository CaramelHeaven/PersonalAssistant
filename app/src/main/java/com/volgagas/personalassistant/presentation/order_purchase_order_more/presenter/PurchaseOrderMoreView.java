package com.volgagas.personalassistant.presentation.order_purchase_order_more.presenter;

import com.volgagas.personalassistant.models.model.order.ServerSubOrder;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:54, 28/02/2019.
 */
public interface PurchaseOrderMoreView extends BaseView {
    void showItems(List<ServerSubOrder> serverSubOrders);
}
