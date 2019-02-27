package com.volgagas.personalassistant.presentation.order_purchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.order_new_purchase.OrderNewPurchaseActivity;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class OrderPurchaseActivity extends MvpAppCompatActivity {

    private Button btnAddNewOrder;
    private Toolbar toolbar;
    private ViewPager vpContainer;
    private TabLayout tabLayout;

    private OrderPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_purchase);
        btnAddNewOrder = findViewById(R.id.btn_add_new_order);
        toolbar = findViewById(R.id.toolbar);
        vpContainer = findViewById(R.id.vp_container);
        tabLayout = findViewById(R.id.view_pager_tab);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pagerAdapter = new OrderPagerAdapter(getSupportFragmentManager());

        vpContainer.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vpContainer);

        toolbar.setNavigationOnClickListener(v -> finish());

        btnAddNewOrder.setOnClickListener(v -> {
            startActivity(new Intent(OrderPurchaseActivity.this, OrderNewPurchaseActivity.class));
        });
    }
}
