package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityPresenter;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityView;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ChooseActionActivity extends BaseActivity implements ChooseActivityView {

    private String action;

    @InjectPresenter
    ChooseActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);
        setPermissionToEnableNfc(false);

        action = getIntent().getStringExtra("ACTION");

        //We can enter in this activity from two ways; ResultActivity or from WorkerTodayDF
        //ADD_MORE_NOMENCLATURES - from ResultActivity, another part - from DF
        switch (action) {
            case "ADD_MORE_NOMENCLATURES":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                NomenclatureFragment.newInstance(getIntent().getParcelableExtra("TASK"),
                                        Constants.ADD_MORE_NOMENCLATURES))
                        .commit();
                break;
            case "USUAL":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ChooseActionFragment
                                .newInstance(getIntent().getParcelableExtra("TASK")))
                        .commit();
                break;
        }
    }

    @Override
    protected void sendDataToServer(String data) {
        if (data.length() == 18) {
            RxBus.getInstance().passScanData(data);
        } else {
            Toast.makeText(this, "Повторите сканирование", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableNFC() {
        setPermissionToEnableNfc(true);
        handlerNFC();
    }
}
