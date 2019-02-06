package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.base.BaseGodActivity;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityPresenter;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityView;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.TwoPermissions;

import timber.log.Timber;

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
        RxBus.getInstance().passScanData(data);
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
    public void enableNFC() {

    }
}
