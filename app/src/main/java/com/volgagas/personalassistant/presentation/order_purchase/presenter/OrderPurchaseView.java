package com.volgagas.personalassistant.presentation.order_purchase.presenter;

import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:56, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface OrderPurchaseView<T> extends BaseView {
    void showItems(List<T> items);
}
