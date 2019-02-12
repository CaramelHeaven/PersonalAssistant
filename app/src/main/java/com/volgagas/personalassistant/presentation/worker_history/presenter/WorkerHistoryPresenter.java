package com.volgagas.personalassistant.presentation.worker_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class WorkerHistoryPresenter extends BasePresenter<WorkerHistoryView<TaskHistory>> {

    private MainRepository repository;

    public WorkerHistoryPresenter() {
        repository = MainRemoteRepository.getInstance();

        //PersonalAssistant.provideDynamics365Auth("dasd", "");
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    protected void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getHistoryTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::unsuccessfulResult));
    }

    private void successfulResult(List<TaskHistory> tasks) {
        getViewState().hideProgress();
        getViewState().showItems(tasks);
    }

    private void unsuccessfulResult(Throwable throwable) {
        Timber.d("THORWABLE: " + throwable.getCause());
        Timber.d("THORWABLE: " + throwable.getMessage());
        Timber.d("unsuccessful: " + throwable.getMessage());
    }
}
