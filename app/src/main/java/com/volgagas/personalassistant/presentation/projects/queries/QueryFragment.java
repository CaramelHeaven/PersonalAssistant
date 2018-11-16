package com.volgagas.personalassistant.presentation.projects.queries;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryPresenter;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryView;
import com.volgagas.personalassistant.presentation.projects.query_create.QueryCreateActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class QueryFragment extends BaseFragment implements QueryView<UniformRequest> {

    private FloatingActionButton fabCreate;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView tvShowEmpty;

    private List<String> listBaseAdapter;
    private QueryBaseAdapter adapterBase;

    @InjectPresenter
    QueryPresenter presenter;

    public static QueryFragment newInstance() {

        Bundle args = new Bundle();

        QueryFragment fragment = new QueryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fabCreate = view.findViewById(R.id.fab_create);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvShowEmpty = view.findViewById(R.id.tv_show_empty_queries);
        progressBar = view.findViewById(R.id.progress_bar);

        listBaseAdapter = new ArrayList<>(Arrays.asList("Заявки к вам", "Заявки от вас"));
        adapterBase = new QueryBaseAdapter(new ArrayList<>());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterBase);

        provideListeners();
    }

    private void provideListeners() {
        fabCreate.setOnClickListener(v -> {
            Timber.d("create");
            startActivity(new Intent(getActivity(), QueryCreateActivity.class));
        });

        adapterBase.setMyOnItemClickListener(position -> {
            Timber.d("CLICK " + position);
            Timber.d("CLICK " + position);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fabCreate = null;
        tvShowEmpty = null;
        progressBar = null;
        recyclerView = null;
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
    public void showItems(List<UniformRequest> items) {
        if (items.size() != 0) {
            adapterBase.updateAdapter(listBaseAdapter, items);
        } else {
            tvShowEmpty.setVisibility(View.VISIBLE);
        }
    }
}
