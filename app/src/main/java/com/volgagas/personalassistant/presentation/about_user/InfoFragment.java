package com.volgagas.personalassistant.presentation.about_user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.presentation.about_user.presenter.InfoPresenter;
import com.volgagas.personalassistant.presentation.about_user.presenter.InfoView;
import com.volgagas.personalassistant.presentation.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class InfoFragment extends BaseFragment implements InfoView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView, recyclerViewSkills;
    private TextView tvSkills;

    private InfoAdapter adapter;
    private SkillsAdapter skillsAdapter;

    @InjectPresenter
    InfoPresenter presenter;

    public static InfoFragment newInstance() {

        Bundle args = new Bundle();

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSkills = view.findViewById(R.id.tv_skills);
        recyclerViewSkills = view.findViewById(R.id.recyclerViewSkills);

        recyclerView.setHasFixedSize(true);
        recyclerViewSkills.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewSkills.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new InfoAdapter(new ArrayList<>());
        skillsAdapter = new SkillsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerViewSkills.setAdapter(skillsAdapter);

        recyclerViewSkills.setNestedScrollingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        progressBar = null;
        tvSkills = null;
        recyclerViewSkills = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tvSkills.setVisibility(View.GONE);
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
    public void showData(List<Object> objects) {
        adapter.updateAdapter(objects);
    }

    @Override
    public void showPersonSkills(List<PersonSkills> objects) {
        if (objects.size() > 0) {
            tvSkills.setVisibility(View.VISIBLE);
            skillsAdapter.updateAdapter(objects);
        }
    }
}
