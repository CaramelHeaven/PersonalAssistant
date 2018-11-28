package com.volgagas.personalassistant.presentation.order_purchase;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.order_purchase.presenter.OrderPurchaseView;

public class OrderPurchaseActivity extends MvpAppCompatActivity implements OrderPurchaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_purchase);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
