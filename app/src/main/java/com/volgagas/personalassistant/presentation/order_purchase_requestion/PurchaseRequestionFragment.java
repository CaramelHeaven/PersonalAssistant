package com.volgagas.personalassistant.presentation.order_purchase_requestion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.order_purchase_requestion.presenter.PurchaseRequestionPresenter;
import com.volgagas.personalassistant.presentation.order_purchase_requestion.presenter.PurchaseRequestionView;
import com.volgagas.personalassistant.presentation.order_purchase_requestion_more.PurchaseReqiestionMoreDialogFragment;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:16, 27/02/2019.
 */
public class PurchaseRequestionFragment extends BaseFragment implements PurchaseRequestionView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout llEmpty;

    private PurchaseOrderAdapter adapter;

    @InjectPresenter
    PurchaseRequestionPresenter presenter;

    public static PurchaseRequestionFragment newInstance() {

        Bundle args = new Bundle();

        PurchaseRequestionFragment fragment = new PurchaseRequestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchase_requestions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        llEmpty = view.findViewById(R.id.ll_empty);

        adapter = new PurchaseOrderAdapter(new ArrayList<>());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            PurchaseReqiestionMoreDialogFragment dialogFragment =
                    PurchaseReqiestionMoreDialogFragment.newInstance(adapter.getRequisitionNumber(position));

            dialogFragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        progressBar = null;
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

    @Override
    public void showItems(List<UserOrder> orders) {
        if (orders.size() > 0) {
            adapter.updateAdapter(orders);
        } else {
            llEmpty.setVisibility(View.VISIBLE);
        }
    }
}
