package com.volgagas.personalassistant.presentation.worker_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

@InjectViewState
public class WorkerHistoryPresenter extends BasePresenter<WorkerHistoryView> {

    public WorkerHistoryPresenter() {
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
