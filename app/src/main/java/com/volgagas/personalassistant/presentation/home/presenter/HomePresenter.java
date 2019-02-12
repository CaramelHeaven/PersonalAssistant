package com.volgagas.personalassistant.presentation.home.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.common.HomeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    public HomePresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
