package com.volgagas.personalassistant.presentation.kiosk_added_tasks.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.CommonChannel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 17:11, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class KioskAddedTaskPresenter extends BasePresenter<KioskAddedTaskView<TaskTemplate>> {

    private CompositeDisposable disposable;
    private MainRepository repository;

    public KioskAddedTaskPresenter() {
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
        disposable.clear();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }
}
