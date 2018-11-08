package com.volgagas.personalassistant.presentation.projects.query_create.fill_request.presenter;

import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.pass_data.PassData;

/**
 * Created by CaramelHeaven on 17:33, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class FillRequestPresenter extends BasePresenter<FillRequestView> {

    public FillRequestPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void handlerClickButton() {
        PassData.getInstance().sendData("s");
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        //nothing
    }
}
