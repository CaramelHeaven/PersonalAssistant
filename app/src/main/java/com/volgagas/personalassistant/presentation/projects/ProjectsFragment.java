package com.volgagas.personalassistant.presentation.projects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;

/**
 * Created by CaramelHeaven on 16:55, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ProjectsFragment extends BaseFragment {

    public static ProjectsFragment newInstance() {

        Bundle args = new Bundle();

        ProjectsFragment fragment = new ProjectsFragment();
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
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
