package com.volgagas.personalassistant.presentation.projects.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import retrofit2.Response;

/**
 * Created by CaramelHeaven on 11:45, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private boolean isOpen = true;
    private MainRepository repository;

    public ProjectsPresenter() {
        super();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }


    @Override
    protected void loadData() {

    }
}
