package com.volgagas.personalassistant.presentation.order_new_base;

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
import com.volgagas.personalassistant.presentation.order_new_base.presenter.OrderNewBasePresenter;
import com.volgagas.personalassistant.presentation.order_new_base.presenter.OrderNewBaseView;
import com.volgagas.personalassistant.presentation.order_new_purchase.OrderCommonAdapter;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.NewOrderModified;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:18, 25/12/2018.
 */
public class OrderNewBaseFragment extends BaseFragment implements OrderNewBaseView {

    private RecyclerView recyclerView;

    private OrderCommonAdapter<NewOrder> adapter;

    @InjectPresenter
    OrderNewBasePresenter presenter;

    public static OrderNewBaseFragment newInstance() {

        Bundle args = new Bundle();

        OrderNewBaseFragment fragment = new OrderNewBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_new_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new OrderCommonAdapter<>(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnButtonPlusMinusClickListener((position, status, count) -> {
            Timber.d("click: " + position);
            NewOrder order = adapter.getItemByPosition(position);
            order.setStatus(status);
            order.setSizeInSheet(count);

            GlobalBus.getEventBus().post(order);
        });
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void callbackFromSheetBottom(NewOrderModified order) {
        if (adapter.getData().contains(order.getNewOrder())) {
            int pos = adapter.getData().indexOf(order.getNewOrder());

            Timber.d("position in newBaseChanged: " + pos);
            adapter.setToSizeNil(pos);
        }
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
