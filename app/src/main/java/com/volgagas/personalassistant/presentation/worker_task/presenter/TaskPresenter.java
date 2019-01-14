package com.volgagas.personalassistant.presentation.worker_task.presenter;

import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 08:53, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskPresenter extends BasePresenter<TaskView<SubTaskViewer>> {

    private Task task;
    private String status;
    private CompositeDisposable disposable;
    private MainRepository repository;

    public TaskPresenter(Task task, String status) {
        this.task = task;
        this.status = status;
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        getViewState().hideProgress();
        getViewState().showError();
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    private void loadData() {
        if (status.equals("TODAY")) {
            disposable.add(repository.getSubTasksToday(task.getIdTask())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        getViewState().hideProgress();
                        getViewState().showItems(result);
                    }, this::handlerErrorsFromBadRequests));
        } else if (status.equals("HISTORY")) {
            disposable.add(repository.getSubTasksHistory(task.getIdTask())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        getViewState().hideProgress();
                        getViewState().showItems(result);
                    }, this::handlerErrorsFromBadRequests));
        }
    }
}
