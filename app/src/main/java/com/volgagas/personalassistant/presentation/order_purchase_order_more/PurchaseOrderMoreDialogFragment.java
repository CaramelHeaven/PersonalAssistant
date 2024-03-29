package com.volgagas.personalassistant.presentation.order_purchase_order_more;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.ServerSubOrder;
import com.volgagas.personalassistant.presentation.order_purchase_order_more.presenter.PurchaseOrderMorePresenter;
import com.volgagas.personalassistant.presentation.order_purchase_order_more.presenter.PurchaseOrderMoreView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:11, 28/02/2019.
 */
public class PurchaseOrderMoreDialogFragment extends MvpAppCompatDialogFragment implements PurchaseOrderMoreView {

    private DisplayMetrics displayMetrics;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private PurchaseOrderMoreAdapter adapter;

    @InjectPresenter
    PurchaseOrderMorePresenter presenter;

    @ProvidePresenter
    PurchaseOrderMorePresenter providePurchaseOrderMorePresenter() {
        return new PurchaseOrderMorePresenter(getArguments().getString("ID"));
    }

    public static PurchaseOrderMoreDialogFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("ID", id);
        PurchaseOrderMoreDialogFragment fragment = new PurchaseOrderMoreDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_purchase_order_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recyclerView);
        toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> dismiss());

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new PurchaseOrderMoreAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 280);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void catastrophicError(Throwable throwable) {

    }

    @Override
    public void showItems(List<ServerSubOrder> serverSubOrders) {
        if (serverSubOrders.size() > 0) {
            adapter.updateAdapter(serverSubOrders);
        }
    }
}
