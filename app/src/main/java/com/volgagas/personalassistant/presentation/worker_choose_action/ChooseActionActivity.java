package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.os.Bundle;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseActivity;

public class ChooseActionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        setPermissionToEnableNfc(false);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ChooseActionFragment
                        .newInstance(getIntent().getParcelableExtra("TASK")))
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
