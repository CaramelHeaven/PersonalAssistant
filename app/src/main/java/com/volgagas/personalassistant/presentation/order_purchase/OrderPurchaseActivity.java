package com.volgagas.personalassistant.presentation.order_purchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.order_new_purchase.OrderNewPurchaseActivity;
import com.volgagas.personalassistant.presentation.order_purchase.presenter.OrderPurchasePresenter;
import com.volgagas.personalassistant.presentation.order_purchase.presenter.OrderPurchaseView;

import java.util.ArrayList;

import timber.log.Timber;

public class OrderPurchaseActivity extends MvpAppCompatActivity implements OrderPurchaseView {

    private Button btnAddNewOrder;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private OrderAdapter adapter;

    @InjectPresenter
    OrderPurchasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_purchase);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddNewOrder = findViewById(R.id.btn_add_new_order);
        progressBar = findViewById(R.id.progressBar);

        btnAddNewOrder.setOnClickListener(v -> {
            startActivity(new Intent(OrderPurchaseActivity.this, OrderNewPurchaseActivity.class));
            Timber.d("click");
            Timber.d("click");
        });

        provideViews();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideViews() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new OrderAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }
}
