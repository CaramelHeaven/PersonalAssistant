package com.volgagas.personalassistant.presentation.order_new_bottom_sheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.NewOrderModified;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:00, 26/12/2018.
 */
public class OrderNewBottomFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private OrderBottomAdapter<NewOrder> adapter;

    public static OrderNewBottomFragment newInstance() {

        Bundle args = new Bundle();

        OrderNewBottomFragment fragment = new OrderNewBottomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_order_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> GlobalBus.getEventBus().post("CLOSE"));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new OrderBottomAdapter<>(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            NewOrder order = adapter.getItemByPosition(position);
            NewOrderModified modified = new NewOrderModified();

            //important code
            modified.setLastCountState(order.getSizeInSheet());

            order.setSizeInSheet(0);
            modified.setNewOrder(order); // and here.

            adapter.removeItemByPosition(position);

            GlobalBus.getEventBus().post(modified);
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

    /* 0 - remove, 1 - add
     * */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void updateAdapter(List<NewOrder> list) {

        adapter.updateAdapter(list);
        Timber.d("UPDATE ADAPTER");
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        toolbar = null;
        super.onDestroyView();
    }
}
