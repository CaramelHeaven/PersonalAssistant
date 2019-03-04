package com.volgagas.personalassistant.presentation.order_purchase_requestion_more;

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
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.presentation.order_purchase_requestion_more.presenter.PurchaseReqiestionMorePresenter;
import com.volgagas.personalassistant.presentation.order_purchase_requestion_more.presenter.PurchaseReqiestionMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 18:46, 27/02/2019.
 */
public class PurchaseReqiestionMoreDialogFragment extends MvpAppCompatDialogFragment implements PurchaseReqiestionMoreView {

    private DisplayMetrics displayMetrics;
    private PurchaseRequisitionMoreAdapter adapter;

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @InjectPresenter
    PurchaseReqiestionMorePresenter presenter;

    @ProvidePresenter
    PurchaseReqiestionMorePresenter providePurchaseReqiestionMorePresenter() {
        return new PurchaseReqiestionMorePresenter(getArguments().getString("ID"));
    }

    public static PurchaseReqiestionMoreDialogFragment newInstance(String orderId) {
        Bundle args = new Bundle();
        args.putString("ID", orderId);

        PurchaseReqiestionMoreDialogFragment fragment = new PurchaseReqiestionMoreDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_purchase_reqiestion_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recyclerView);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> dismiss());

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new PurchaseRequisitionMoreAdapter(new ArrayList<>());
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
        recyclerView = null;
        progressBar = null;
        super.onDestroyView();
    }

    @Override
    public void showItem(List<UserSubOrder> userSubOrder) {
        if (userSubOrder.size() > 0) {
            adapter.updateAdapter(userSubOrder);
        } else {
            Toast.makeText(getActivity(), "Ничего нет", Toast.LENGTH_SHORT).show();
        }
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
        Toast.makeText(getActivity(), "Ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
