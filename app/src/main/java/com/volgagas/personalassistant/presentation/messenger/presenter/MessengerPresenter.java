package com.volgagas.personalassistant.presentation.messenger.presenter;

import com.volgagas.personalassistant.presentation.base.BasePresenter;

/**
 * Created by CaramelHeaven on 12:29, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
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
}
