package com.volgagas.personalassistant.presentation.projects_messenger.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.models.model.Message;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.services.ReplyWorker;

import java.util.List;
import java.util.UUID;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 12:29, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class MessengerPresenter extends BasePresenter<MessengerView> {

    public MessengerPresenter() {

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

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {

    }

    public Observable<UUID> postMessage(Message message) {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(ReplyWorker.class)
                .setConstraints(new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build())
                .build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

        return Observable.just(oneTimeWorkRequest.getId());
    }
}
