package com.volgagas.personalassistant.presentation.projects_query_from_user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects_query_from_user.presenter.QueryFromUserView;
import com.volgagas.personalassistant.presentation.projects_query_from_user.presenter.UniformsPresenter;
import com.volgagas.personalassistant.presentation.projects_query_more_details.QueryMoreDetailsDialogFragment;
import com.volgagas.personalassistant.presentation.query_create.QueryCreateActivity;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:24, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryFromUserFragment extends BaseFragment implements QueryFromUserView<UniformRequest> {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ImageView ivEmptyTasks;
    private TextView tvEmptyTasks;
    private FloatingActionButton fabCreate;
    private SwipeRefreshLayout swipeRefresh;

    private QueryFromUserAdapter adapter;

    @InjectPresenter
    UniformsPresenter presenter;

    public static QueryFromUserFragment newInstance() {

        Bundle args = new Bundle();

        QueryFromUserFragment fragment = new QueryFromUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query_from_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvEmptyTasks = view.findViewById(R.id.tv_empty_tasks);
        fabCreate = view.findViewById(R.id.fab_create);
        ivEmptyTasks = view.findViewById(R.id.iv_empty_tasks);
        swipeRefresh = view.findViewById(R.id.spl_update_content);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new QueryFromUserAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            QueryMoreDetailsDialogFragment fragment =
                    QueryMoreDetailsDialogFragment.newInstance(adapter.getItemByPosition(position));
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });

        fabCreate.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QueryCreateActivity.class)));

        provideRefreshingItems();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        ivEmptyTasks.setVisibility(View.GONE);
        tvEmptyTasks.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
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
    public void showItems(List items) {
        if (items.size() > 0) {
            Timber.d("UPDATE");
            adapter.updateAdapter(items);
        } else {
            ivEmptyTasks.setVisibility(View.VISIBLE);
            tvEmptyTasks.setVisibility(View.VISIBLE);
        }
    }

    private void provideRefreshingItems() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefresh.setOnRefreshListener(() -> {
            adapter.clear();
            presenter.updateData();
            if (swipeRefresh.isRefreshing()) {
                new Handler().postDelayed(() -> swipeRefresh.setRefreshing(false), 1000);
            }
        });
    }

    @Override
    public void showUpdatedData(List<UniformRequest> data) {
        if (data.size() > 0) {
            adapter.updateAdapter(data);
        } else {
            ivEmptyTasks.setVisibility(View.VISIBLE);
            tvEmptyTasks.setVisibility(View.VISIBLE);
        }
    }
}
