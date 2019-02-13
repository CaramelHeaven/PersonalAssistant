package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.presentation.order_new_bottom_sheet.OrderNewBottomFragment;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchasePresenter;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchaseView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.NewOrderModified;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class OrderNewPurchaseActivity extends MvpAppCompatActivity implements OrderNewPurchaseView<Order> {

    private ViewPager vpContainer;
    private Button btnCountItems, btnConfirm;
    private RelativeLayout rlContainer;
    private BottomSheetBehavior bottomSheetBehavior;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    private OrderViewPager pagerAdapter;

    @InjectPresenter
    OrderNewPurchasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_new_purchase);
        vpContainer = findViewById(R.id.vp_container);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnCountItems = findViewById(R.id.btn_count_items);
        rlContainer = findViewById(R.id.rl_container);
        bottomSheetBehavior = BottomSheetBehavior.from(rlContainer);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tl_container);

        setSupportActionBar(toolbar);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO clean up it!
        toolbar.setNavigationOnClickListener(v -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            } else {
                finish();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, OrderNewBottomFragment.newInstance())
                .commit();

        btnCountItems.setOnClickListener(v -> {
            Timber.d("lalal");
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            GlobalBus.getEventBus().post(presenter.getChosenOrders());
        });

        btnConfirm.setOnClickListener(v -> {
            if (presenter.getChosenOrders().size() == 0) {
                Toast.makeText(OrderNewPurchaseActivity.this, "Корзина пуста", Toast.LENGTH_SHORT).show();
            } else {
                presenter.sendData();
            }
        });

        provideTabLayoutViewPager();
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getEventBus().unregister(this);
        super.onStop();
    }

    /* 0 - remove, 1 - add
     * */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void addedTaskToList(NewOrder newOrder) {
        Timber.d("checking: " + newOrder);
        if (newOrder.getStatus() == 0) {
            presenter.removeNewOrder(newOrder);
        } else {
            presenter.addNewOrder(newOrder);
        }

        btnCountItems.setText("Кол-во: " + String.valueOf(presenter.getChosenOrders().size()));
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void callbackRemoveNewOrderModified(NewOrderModified newOrder) {
        Timber.d("callbackRemoveNewOrderModified");

        Timber.d("size before: " + presenter.getChosenOrders().size());
        for (int i = 0; i < newOrder.getLastCountState(); i++) {
            presenter.getChosenOrders().remove(newOrder.getNewOrder());
        }

        Timber.d("size after: " + presenter.getChosenOrders().size());

        btnCountItems.setText("Кол-во: " + String.valueOf(presenter.getChosenOrders().size()));
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void closeOrderSheetFragment(String close) {
        if (close.equals("CLOSE")) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void provideTabLayoutViewPager() {
        pagerAdapter = new OrderViewPager(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(vpContainer);

        if (!getResources().getBoolean(R.bool.isTablet)) {
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    coloringTabName(true, tab.getCustomView());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    coloringTabName(false, tab.getCustomView());
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            provideCustomTabNames();

            //small feature [bug]
            vpContainer.setCurrentItem(1);
            vpContainer.setCurrentItem(0);
        }
    }

    private void provideCustomTabNames() {
        CardView firstTab = (CardView) LayoutInflater.from(this).inflate(R.layout.item_custom_tab_layout, null);
        TextView tv1 = firstTab.findViewById(R.id.tv_name);
        tv1.setText("Основное");
        tabLayout.getTabAt(0).setCustomView(firstTab);

        CardView secondTab = (CardView) LayoutInflater.from(this).inflate(R.layout.item_custom_tab_layout, null);
        TextView tv = secondTab.findViewById(R.id.tv_name);
        tv.setText("Дополнительное");
        tabLayout.getTabAt(1).setCustomView(secondTab);
    }

    private void coloringTabName(boolean coloring, View view) {
        if (coloring) {
            CardView cvContainer = view.findViewById(R.id.cd_container);
            cvContainer.setRadius(10);
            cvContainer.setCardElevation(0);
            TextView tv = view.findViewById(R.id.tv_name);
            cvContainer.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            CardView cvContainer = view.findViewById(R.id.cd_container);
            cvContainer.setRadius(10);
            cvContainer.setCardElevation(0);
            TextView tv = view.findViewById(R.id.tv_name);
            cvContainer.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
