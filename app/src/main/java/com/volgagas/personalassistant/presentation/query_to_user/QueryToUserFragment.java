package com.volgagas.personalassistant.presentation.query_to_user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.query_more_details.QueryMoreDetailsDialogFragment;
import com.volgagas.personalassistant.presentation.query_to_user.presenter.QueryToUserPresenter;
import com.volgagas.personalassistant.presentation.query_to_user.presenter.QueryToUserView;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:25, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryToUserFragment extends BaseFragment implements QueryToUserView<QueryToUser> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageView ivImageEmpty;
    private TextView tvEmptyQueries;

    private QueryToUserAdapter adapter;

    @InjectPresenter
    QueryToUserPresenter presenter;

    public static QueryToUserFragment newInstance() {

        Bundle args = new Bundle();

        QueryToUserFragment fragment = new QueryToUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query_to_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        tvEmptyQueries = view.findViewById(R.id.tv_empty_tasks);
        ivImageEmpty = view.findViewById(R.id.iv_empty_tasks);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new QueryToUserAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            QueryMoreDetailsDialogFragment fragment =
                    QueryMoreDetailsDialogFragment.newInstance(adapter.getItemByPosition(position));
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        ivImageEmpty.setVisibility(View.GONE);
        tvEmptyQueries.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List items) {
        if (items.size() > 0) {
            adapter.updateAdapter(items);
        } else {
            ivImageEmpty.setVisibility(View.VISIBLE);
            tvEmptyQueries.setVisibility(View.VISIBLE);
        }
    }

}
