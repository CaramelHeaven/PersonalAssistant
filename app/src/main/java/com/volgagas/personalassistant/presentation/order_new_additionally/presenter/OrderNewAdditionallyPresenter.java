package com.volgagas.personalassistant.presentation.order_new_additionally.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CaramelHeaven on 13:19, 25/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class OrderNewAdditionallyPresenter extends MvpPresenter<OrderNewAdditionallyView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public OrderNewAdditionallyPresenter() {
        disposable = new CompositeDisposable();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposable.add(repository.getOrderNewAdditionally()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().showItems(result);
                }, Crashlytics::logException));

    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
