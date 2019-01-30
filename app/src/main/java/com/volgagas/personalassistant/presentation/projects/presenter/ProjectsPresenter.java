package com.volgagas.personalassistant.presentation.projects.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 11:45, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private boolean isOpen = true;

    public ProjectsPresenter() {
        super();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        provideListenerActions();
    }

    private void provideListenerActions() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {

    }
}
