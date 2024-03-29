package com.volgagas.personalassistant.presentation.worker_choose_action.presenter;

import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ChooseActionPresenter extends BasePresenter<ChooseView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public ChooseActionPresenter() {
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

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {

    }

    /**
     * When user's current location is nomenclature scan, we send scanned data to server and check
     * if it is the true nomenclature
     */
    public void sendScannedData() {
        //lalala
    }
}
