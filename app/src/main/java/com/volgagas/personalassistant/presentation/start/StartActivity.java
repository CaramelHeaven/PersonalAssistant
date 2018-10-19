package com.volgagas.personalassistant.presentation.start;

import android.content.Intent;
import android.os.Bundle;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.main.MainActivity;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;

public class StartActivity extends BaseActivity implements StartView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void sendDataToServer(String data) {
        //nothing
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
