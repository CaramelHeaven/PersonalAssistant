package com.volgagas.personalassistant.presentation.kiosk.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.CommonChannel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:53, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class KioskPresenter extends BasePresenter<KioskView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private boolean permissionToSend = false;

    private List<TaskTemplate> addedTasks;

    public KioskPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
        listenerAddedTasks();
        addedTasks = new ArrayList<>();
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

    @SuppressLint("CheckResult")
    private void listenerAddedTasks() {
        CommonChannel.getObservableUpdatedTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    addedTasks.clear();
                    addedTasks.addAll(result);
                    Timber.d("checking size: " + addedTasks.size());
                });
    }

    public int getAddedTasksSize() {
        return addedTasks.size();
    }

    public boolean isPermissionToSend() {
        return permissionToSend;
    }

    public void setPermissionToSend(boolean permissionToSend) {
        this.permissionToSend = permissionToSend;
    }

    public void sendData() {
        Timber.d("lala; " + addedTasks.toString());
    }
}
