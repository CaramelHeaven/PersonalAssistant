package com.volgagas.personalassistant.presentation.kiosk_added_tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.kiosk_added_tasks.presenter.KioskAddedTaskPresenter;
import com.volgagas.personalassistant.presentation.kiosk_added_tasks.presenter.KioskAddedTaskView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.AddedTask;
import com.volgagas.personalassistant.utils.item_touch.ItemTouchAdapterKioskAdded;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:11, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskAddedTaskFragment extends BaseFragment implements KioskAddedTaskView<Task> {

    private RecyclerView recyclerView;
    private TextView tvShowEmpty;

    private KioskAddedTaskAdapter adapter;

    @InjectPresenter
    KioskAddedTaskPresenter presenter;

    public static KioskAddedTaskFragment newInstance() {

        Bundle args = new Bundle();

        KioskAddedTaskFragment fragment = new KioskAddedTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kiosk_added_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        tvShowEmpty = view.findViewById(R.id.tv_empty_tasks);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new KioskAddedTaskAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ItemTouchAdapterKioskAdded callback = new ItemTouchAdapterKioskAdded(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        //listener for removing item
        adapter.setMyOnItemClickListener(position ->
                presenter.removeTask(adapter.getItemByPosition(position)));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        tvShowEmpty = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
    public void addedTaskToList(AddedTask data) {
        Timber.d("checking: " + data.toString());
        if (tvShowEmpty.getVisibility() == View.VISIBLE) {
            tvShowEmpty.setVisibility(View.GONE);
        }
        adapter.addItem(data.getTask());
        presenter.addedTask(data.getTask());
    }
}
