package com.volgagas.personalassistant.presentation.contracts;

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
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractPresenter;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractView;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ContractFragment extends BaseFragment implements ContractView<Contract> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmptyContracts;
    private ImageView ivEmptyContracts;

    private ContractAdapter adapter;

    @InjectPresenter
    ContractPresenter presenter;

    public static ContractFragment newInstance() {

        Bundle args = new Bundle();

        ContractFragment fragment = new ContractFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contracts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        tvEmptyContracts = view.findViewById(R.id.tv_empty_tasks);
        ivEmptyContracts = view.findViewById(R.id.iv_empty_tasks);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        ivEmptyContracts = null;
        tvEmptyContracts = null;
        progressBar = null;
        recyclerView = null;
        super.onDestroyView();
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new ContractAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            Timber.d("clicK: " + position);
            Timber.d("clicK: " + position);
        });
    }

    @Override
    public void showProgress() {
        ivEmptyContracts.setVisibility(View.GONE);
        tvEmptyContracts.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContracts(List<Contract> values) {
        if (values.size() > 0) {
            adapter.updateAdapter(values);
        } else {
            ivEmptyContracts.setVisibility(View.VISIBLE);
            ivEmptyContracts.setVisibility(View.VISIBLE);
        }
    }
}
