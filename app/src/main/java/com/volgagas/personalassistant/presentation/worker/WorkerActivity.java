package com.volgagas.personalassistant.presentation.worker;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerPresenter;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerView;

public class WorkerActivity extends MvpAppCompatActivity implements WorkerView {

    @InjectPresenter
    WorkerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
