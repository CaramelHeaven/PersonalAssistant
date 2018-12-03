package com.volgagas.personalassistant.presentation.worker_gpa.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

@InjectViewState
public class GpaPresenter extends BasePresenter<GpaView> {

    public GpaPresenter() {
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
