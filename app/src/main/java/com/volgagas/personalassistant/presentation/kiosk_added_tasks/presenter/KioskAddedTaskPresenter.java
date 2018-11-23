package com.volgagas.personalassistant.presentation.kiosk_added_tasks.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.CommonChannel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 17:11, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class KioskAddedTaskPresenter extends BasePresenter<KioskAddedTaskView<Task>> {

    private CompositeDisposable disposable;
    private MainRepository repository;

    private Set<Task> addedTasks;

    public KioskAddedTaskPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
        addedTasks = new LinkedHashSet<>();
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

    public void addedTask(Task task) {
        addedTasks.add(task);
        CommonChannel.sendListTasks(addedTasks);
    }

    public void removeTask(Task task) {
        addedTasks.remove(task);
        CommonChannel.sendListTasks(addedTasks);
    }
}
