package com.volgagas.personalassistant.presentation.kiosk.presenter;

import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 16:53, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskPresenter extends BasePresenter<KioskView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public KioskPresenter() {
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
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }
}
