package com.volgagas.personalassistant.presentation.worker_result.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    public ResultPresenter() {
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
