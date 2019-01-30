package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpPresenter;
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

    protected void handlerAuthenticationRepeat() {
        RxBus.getInstance().passActionForUpdateToken("UPDATE_TOKEN_PRESENTER");
    }

    protected abstract void tokenUpdatedCallLoadDataAgain();

    protected abstract void loadData();

    /**
     * If token updated, all listeners comes here and we repeat request which failed cause 401
     * exception
     */
    public void setListenerUpdatedToken() {
        Timber.d("setListenerUpdatedToken!");
        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals("UPDATE_TOKEN_PRESENTER"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("checking result FUCK YOU: " + result);

                    Timber.d("IM INSIDE RESULT FUCK YOU");
                    if (result.equals("TOKEN_UPDATED_PRESENTER"))
                        loadData();
                }));
    }

    public void clearListenerIfScreenNotVisible() {
        Timber.d("listener removed!");
        disposable.clear();
    }
}
