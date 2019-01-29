package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:43, 01.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class BasePresenter<S extends BaseView> extends MvpPresenter<S> {

    /**
     * Base handler for catch errors from server
     */
    protected abstract void handlerErrorsFromBadRequests(Throwable throwable);

    /**
     * Dynamics can response error in the successful result to us.
     */
    protected abstract void handlerErrorInSuccessfulResult(List<Response<Void>> result);
}
