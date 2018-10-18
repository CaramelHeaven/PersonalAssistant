package com.volgagas.personalassistant.presentation.start;

import android.os.Bundle;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;

public class StartActivity extends BaseActivity implements StartView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
