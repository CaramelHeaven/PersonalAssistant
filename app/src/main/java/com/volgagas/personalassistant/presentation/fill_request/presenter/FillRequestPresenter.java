package com.volgagas.personalassistant.presentation.fill_request.presenter;

import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

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

    public void handlerClickButton(RequestData data) {
        PassDataChannel.getInstance().sendData(data);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        //nothing
    }
}