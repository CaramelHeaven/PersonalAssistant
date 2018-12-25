package com.volgagas.personalassistant.presentation.order_new_base.presenter;

import com.arellomobile.mvp.MvpView;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:19, 25/12/2018.
 */
public interface OrderNewBaseView extends MvpView {
    void showItems(List<NewOrder> orders);
}