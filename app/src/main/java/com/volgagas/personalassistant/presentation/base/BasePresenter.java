package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:43, 01.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class BasePresenter<S extends BaseView> extends MvpPresenter<S> {

    public BasePresenter() {
        Timber.d("CALL BASE PRESENTER");
    }

    @Override
    protected void onFirstViewAttach() {
        Timber.d("CALL FIRST ATTACH");
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void handlerErrorsFromBadRequests(Throwable throwable);
}
