package com.volgagas.personalassistant.presentation.projects.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 11:45, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private CompositeDisposable disposable;
    private boolean isOpen = true;

    public ProjectsPresenter() {
        disposable = new CompositeDisposable();
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
}
