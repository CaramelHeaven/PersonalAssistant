package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.GpaActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;

public class ChooseActionActivity extends BaseActivity {

    private Button btnToNomenclature, btnToGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        btnToGpa = findViewById(R.id.btn_to_gpa);
        btnToNomenclature = findViewById(R.id.btn_to_nomenclature);

        setPermissionToEnableNfc(false);
        handlerNFC();

        btnToGpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActionActivity.this, GpaActivity.class));
            }
        });

        btnToNomenclature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, NomenclatureFragment.newInstance())
                        .commit();
            }
        });
    }

    @Override
    protected void sendDataToServer(String data) {

    }
}
