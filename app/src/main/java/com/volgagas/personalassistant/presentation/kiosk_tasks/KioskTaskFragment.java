package com.volgagas.personalassistant.presentation.kiosk_tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.kiosk_tasks.presenter.KioskTaskPresenter;
import com.volgagas.personalassistant.presentation.kiosk_tasks.presenter.KioskTaskView;

/**
 * Created by CaramelHeaven on 17:10, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskTaskFragment extends BaseFragment implements KioskTaskView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @InjectPresenter
    KioskTaskPresenter presenter;

    public static KioskTaskFragment newInstance() {

        Bundle args = new Bundle();

        KioskTaskFragment fragment = new KioskTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kiosk_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
