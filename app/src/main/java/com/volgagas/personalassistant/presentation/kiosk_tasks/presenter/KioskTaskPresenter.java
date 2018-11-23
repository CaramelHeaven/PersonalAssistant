package com.volgagas.personalassistant.presentation.kiosk_tasks.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.CommonChannel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 17:12, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class KioskTaskPresenter extends BasePresenter<KioskTaskView<Task>> {

    private CompositeDisposable disposable;
    private MainRepository repository;

    public KioskTaskPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showItems(repository.testedData());
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
        CommonChannel.sendChosenTask(task);
    }

}
