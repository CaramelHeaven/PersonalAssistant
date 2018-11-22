package com.volgagas.personalassistant.presentation.order_purchase.presenter;

import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 16:56, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class OrderPurchasePresenter extends BasePresenter<OrderPurchaseView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public OrderPurchasePresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
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
