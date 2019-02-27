package com.volgagas.personalassistant.presentation.projects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects.presenter.ProjectsPresenter;
import com.volgagas.personalassistant.presentation.projects.presenter.ProjectsView;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:34, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class FragmentProjects extends BaseFragment implements ProjectsView {

    private PagerProjectsAdapter adapter;

    //two flags for control user scroll inside current view pager
    private boolean stateSecondFragment = false;
    private boolean stateThirdFragment = false;

    private ViewPager vpContainer;
    private TabLayout tabLayout;

    @InjectPresenter
    ProjectsPresenter presenter;

    public static FragmentProjects newInstance() {

        Bundle args = new Bundle();

        FragmentProjects fragment = new FragmentProjects();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vpContainer = view.findViewById(R.id.vp_container);
        tabLayout = view.findViewById(R.id.view_pager_tab);

        adapter = new PagerProjectsAdapter(getActivity().getSupportFragmentManager());
        vpContainer.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContainer);
    }

    @Override
    public void onStop() {
        stateSecondFragment = !stateSecondFragment;
        stateThirdFragment = !stateThirdFragment;
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        vpContainer = null;
        tabLayout = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(getActivity(), "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getActivity(), "Ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
