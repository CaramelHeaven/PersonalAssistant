package com.volgagas.personalassistant.presentation.order_new_additionally;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.order_new_additionally.presenter.OrderNewAdditionallyPresenter;
import com.volgagas.personalassistant.presentation.order_new_additionally.presenter.OrderNewAdditionallyView;
import com.volgagas.personalassistant.presentation.order_new_purchase.OrderCommonAdapter;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:18, 25/12/2018.
 */
public class OrderNewAdditionallyFragment extends BaseFragment implements OrderNewAdditionallyView {

    private RecyclerView recyclerView;

    private OrderCommonAdapter<NewOrder> adapter;

    @InjectPresenter
    OrderNewAdditionallyPresenter presenter;

    public static OrderNewAdditionallyFragment newInstance() {

        Bundle args = new Bundle();

        OrderNewAdditionallyFragment fragment = new OrderNewAdditionallyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_new_additionally, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new OrderCommonAdapter<>(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnButtonPlusMinusClickListener((position, status) -> {
            Timber.d("click: " + position);
            NewOrder order = adapter.getItemByPosition(position);
            order.setStatus(status);

            GlobalBus.getEventBus().post(order);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showItems(List<NewOrder> orders) {
        adapter.updateAdapter(orders);
    }
}
