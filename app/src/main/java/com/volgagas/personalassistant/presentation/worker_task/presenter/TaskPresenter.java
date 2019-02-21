package com.volgagas.personalassistant.presentation.worker_task.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.common.GlobalTask;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 08:53, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class TaskPresenter extends BasePresenter<TaskView<SubTaskViewer>> {

    private GlobalTask task;
    private String status;
    private MainRepository repository;

    public TaskPresenter(GlobalTask task, String status) {
        super();
        this.task = task;
        this.status = status;
        repository = MainRemoteRepository.getInstance();
    }

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
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            //todo make repeat token
        } else {
            sendCrashlytics(throwable);
            getViewState().hideProgress();
            getViewState().showError();
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().showProgress();
        if (status.equals("TODAY") && task instanceof Task) {
            disposable.add(repository.getSubTasksToday(((Task) task).getIdTask())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        getViewState().hideProgress();
                        getViewState().showItems(result);
                    }, this::handlerErrorsFromBadRequests));
        } else if (status.equals("HISTORY") && task instanceof TaskHistory) {
            disposable.add(repository.getSubTasksHistory(((TaskHistory) task).getIdTask())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        getViewState().hideProgress();
                        getViewState().showItems(result);
                    }, this::handlerErrorsFromBadRequests));
        }
    }

    public GlobalTask getGlobalTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }
}
