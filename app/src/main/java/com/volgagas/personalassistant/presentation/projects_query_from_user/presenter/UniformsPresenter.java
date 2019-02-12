package com.volgagas.personalassistant.presentation.projects_query_from_user.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:23, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class UniformsPresenter extends BasePresenter<QueryFromUserView<UniformRequest>> {

    private MainRepository repository;

    public UniformsPresenter() {
        repository = MainRemoteRepository.getInstance();

        PersonalAssistant.provideDynamics365Auth("asf", "");
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void result(List<UniformRequest> requests) {
        getViewState().hideProgress();
        getViewState().showItems(requests);
    }

    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        Timber.d("throwable: " + throwable.getCause());
        Timber.d("throwable: " + throwable.getMessage());
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        disposable.add(repository.getUniformRequestsFromUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::result, this::handlerErrorsFromBadRequests));
    }
}
