package com.volgagas.personalassistant.presentation.order_purchase_order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.order_purchase_order.presenter.PurchaseOrderPresenter;
import com.volgagas.personalassistant.presentation.order_purchase_order.presenter.PurchaseOrderView;

/**
 * Created by CaramelHeaven on 16:16, 27/02/2019.
 */
public class PurchaseOrderFragment extends BaseFragment implements PurchaseOrderView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout llEmpty;

    @InjectPresenter
    PurchaseOrderPresenter presenter;

    public static PurchaseOrderFragment newInstance() {

        Bundle args = new Bundle();

        PurchaseOrderFragment fragment = new PurchaseOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchase_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        llEmpty = view.findViewById(R.id.ll_empty);
    }

    @Override
    public void onDestroyView() {
        progressBar = null;
        recyclerView = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        llEmpty.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void catastrophicError(Throwable throwable) {

    }
}
