package com.volgagas.personalassistant.presentation.home.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.common.HomeModel;

import java.util.ArrayList;
import java.util.List;

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
