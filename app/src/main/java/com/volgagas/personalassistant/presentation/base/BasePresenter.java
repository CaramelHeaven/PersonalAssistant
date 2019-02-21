package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpPresenter;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:43, 01.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class BasePresenter<S extends BaseView> extends MvpPresenter<S> {

    protected CompositeDisposable disposable;

    public BasePresenter() {
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    /**
     * Base handler for catch errors from server
     */
    protected abstract void handlerErrorsFromBadRequests(Throwable throwable);

    /**
     * Dynamics can response error in the successful result to us.
     */
    protected abstract void handlerErrorInSuccessfulResult(List<Response<Void>> result);

    /**
     * Load base data for current screen
     */
    protected abstract void loadData();

    /**
     * If we get error from network request - send to crashlytics server for handler it
     */
    protected void sendCrashlytics(Throwable throwable) {
        Crashlytics.logException(throwable);
    }
}
