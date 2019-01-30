package com.volgagas.personalassistant.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.utils.threads.UpdateTokenHandler;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private UpdateTokenHandler updateTokenHandler;

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        updateTokenHandler = new UpdateTokenHandler("UpdateTokenHandler");
        updateTokenHandler.start();
    }

    @Override
    public void onDestroy() {
        updateTokenHandler.removePeriodicWork();
        updateTokenHandler.quit();

        super.onDestroy();
    }
}
