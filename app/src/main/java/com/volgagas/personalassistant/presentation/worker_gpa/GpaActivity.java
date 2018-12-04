package com.volgagas.personalassistant.presentation.worker_gpa;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaPresenter;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaView;

public class GpaActivity extends BaseActivity implements GpaView {

    private ProgressBar progressBar;
    private TextView tvScanGpa;
    private TextView tvUncorrected;

    @InjectPresenter
    GpaPresenter presenter;

    @ProvidePresenter
    GpaPresenter provideTaskDialogPresenter() {
        return new GpaPresenter(getIntent().getParcelableExtra("TASK"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        progressBar = findViewById(R.id.progressBar);
        tvScanGpa = findViewById(R.id.tv_scanGpa);
        tvUncorrected = findViewById(R.id.tv_uncorrectedPlace);

        setPermissionToEnableNfc(true);


    }

    @Override
    protected void sendDataToServer(String data) {
        //TODO check GPA
        presenter.sendData(data);
        setPermissionToEnableNfc(false);
    }

    @Override
    public void showProgress() {
        tvScanGpa.setVisibility(View.GONE);
        tvUncorrected.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        setPermissionToEnableNfc(true);
    }

    @Override
    public void completed() {

    }
}
