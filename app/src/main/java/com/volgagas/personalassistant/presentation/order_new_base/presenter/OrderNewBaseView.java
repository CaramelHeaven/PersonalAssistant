package com.volgagas.personalassistant.presentation.order_new_base.presenter;

import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:19, 25/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface OrderNewBaseView extends BaseView {
    void showItems(List<NewOrder> orders);
}
