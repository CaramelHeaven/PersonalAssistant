package com.volgagas.personalassistant.presentation.worker_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

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

        //Listener for get data from WorkerTaskToday. We use Single.zip for it.
        disposable.add(RxBus.getInstance().getCommonChannel()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_HISTORY_COMES_DATA))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showItems(CachePot.getInstance().getTaskHistories());
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CachePot.getInstance().clearTaskHistories();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    /**
     * We remain it here for something if the future
     */
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
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_HISTORY_PRESENTER);
        } else {
            Timber.d("THORWABLE: " + throwable.getCause());
            Timber.d("THORWABLE: " + throwable.getMessage());
        }
    }
}
