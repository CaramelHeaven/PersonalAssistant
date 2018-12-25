package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchasePresenter;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchaseView;

public class OrderNewPurchaseActivity extends MvpAppCompatActivity implements OrderNewPurchaseView<Order> {

    private ViewPager vpContainer;
    private Button btnCountItems, btnConfirm;

    private OrderViewPager pagerAdapter;

    @InjectPresenter
    OrderNewPurchasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_new_purchase);
        vpContainer = findViewById(R.id.vp_container);

        pagerAdapter = new OrderViewPager(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
