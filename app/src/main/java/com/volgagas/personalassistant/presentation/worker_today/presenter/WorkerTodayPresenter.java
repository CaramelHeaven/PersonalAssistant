package com.volgagas.personalassistant.presentation.worker_today.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

@InjectViewState
public class WorkerTodayPresenter extends BasePresenter<WorkerTodayView> {

    public WorkerTodayPresenter() {

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
}
