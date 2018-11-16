package com.volgagas.personalassistant.presentation.messenger;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerPresenter;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerView;

public class MessengerActivity extends MvpAppCompatActivity implements MessengerView {

    @InjectPresenter
    MessengerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
