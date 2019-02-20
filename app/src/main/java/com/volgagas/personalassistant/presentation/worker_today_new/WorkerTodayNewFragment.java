package com.volgagas.personalassistant.presentation.worker_today_new;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_task.TaskDialogFragment;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewPresenter;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewView;
import com.volgagas.personalassistant.utils.views.sticky_header.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:48, 23/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class WorkerTodayNewFragment extends BaseFragment implements WorkerTodayNewView<Task> {

    private TodayNewAdapter adapter;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private ImageView ivEmptyTasks;
    private TextView tvEmptyTasks;

    @InjectPresenter
    WorkerTodayNewPresenter presenter;

    public static WorkerTodayNewFragment newInstance() {

        Bundle args = new Bundle();

        WorkerTodayNewFragment fragment = new WorkerTodayNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker_today_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        ivEmptyTasks = view.findViewById(R.id.iv_empty_tasks);
        tvEmptyTasks = view.findViewById(R.id.tv_empty_tasks);
        swipeRefresh = view.findViewById(R.id.spl_update_content);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new TodayNewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));

        adapter.setOnTaskItemClickListener((position) -> {
            TaskDialogFragment fragment = TaskDialogFragment
                    .newInstance(adapter.getItem(position), "TODAY");
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });


        provideRefreshingItems();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        swipeRefresh = null;
        recyclerView = null;
        tvEmptyTasks = null;
        ivEmptyTasks = null;
        progressBar = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        ivEmptyTasks.setVisibility(View.GONE);
        tvEmptyTasks.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(getActivity(), "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItems(List<Task> models) {
        if (models.size() > 0) {
            adapter.updateItems(models);
        } else {
            tvEmptyTasks.setVisibility(View.VISIBLE);
            ivEmptyTasks.setVisibility(View.VISIBLE);
        }
    }

    private void provideRefreshingItems() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefresh.setOnRefreshListener(() -> {
            adapter.clear();
            presenter.loadData();
            if (swipeRefresh.isRefreshing()) {
                new Handler().postDelayed(() -> swipeRefresh.setRefreshing(false), 1000);
            }
        });
    }
}


