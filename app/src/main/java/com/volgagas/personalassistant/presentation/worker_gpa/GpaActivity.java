package com.volgagas.personalassistant.presentation.worker_gpa;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaPresenter;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaView;

public class GpaActivity extends BaseActivity implements GpaView {

    @InjectPresenter
    GpaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
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
