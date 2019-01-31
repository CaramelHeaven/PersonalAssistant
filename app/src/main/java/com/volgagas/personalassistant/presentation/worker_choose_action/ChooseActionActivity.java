package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityPresenter;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityView;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.NomenclatureBarcodeActivity;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

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
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, ChooseActionFragment
//                                .newInstance(getIntent().getParcelableExtra("TASK")))
//                        .commit();
                startActivity(new Intent(ChooseActionActivity.this, NomenclatureBarcodeActivity.class));
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

    @Override
    public void initialBasePresenter() {
        setBasePresenter(presenter);
    }
}
