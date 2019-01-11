package com.volgagas.personalassistant.presentation.worker_gpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaPresenter;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaView;
import com.volgagas.personalassistant.presentation.worker_result.ResultActivity;

import java.util.ArrayList;

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
        Toast.makeText(this, "Подтверждено о начале работы", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(GpaActivity.this, ResultActivity.class);

        intent.putParcelableArrayListExtra("LIST_SUB_TASKS", new ArrayList<>(presenter.getSubTaskList()));
        intent.putExtra("TASK", presenter.getTask());

        startActivity(intent);
        finish();
    }
}
