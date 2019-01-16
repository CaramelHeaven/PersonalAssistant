package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.GpaActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;

import timber.log.Timber;

public class ChooseActionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        setPermissionToEnableNfc(false);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ChooseActionFragment.newInstance())
                .commit();
    }

    @Override
    protected void sendDataToServer(String data) {

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
}
