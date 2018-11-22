package com.volgagas.personalassistant.presentation.worker.presenter;

import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 16:52, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class WorkerPresenter extends BasePresenter<WorkerView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public WorkerPresenter() {

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
}
