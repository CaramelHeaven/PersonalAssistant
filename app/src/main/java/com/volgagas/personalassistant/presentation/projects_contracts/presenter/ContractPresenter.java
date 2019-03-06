package com.volgagas.personalassistant.presentation.projects_contracts.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ContractPresenter extends BasePresenter<ContractView<Contract>> {

    private MainRepository repository;

    public ContractPresenter() {
        repository = MainRemoteRepository.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        Timber.d("error: " + throwable.getMessage());
        //okay, we have exception ;)
        Crashlytics.logException(throwable);
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        //nothing
    }

    @Override
    public void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getContractsForUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showContracts(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
