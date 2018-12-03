package com.volgagas.personalassistant.presentation.worker_result;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultPresenter;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultView;

public class ResultActivity extends BaseActivity implements ResultView {

    @InjectPresenter
    ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    protected void sendDataToServer(String data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
