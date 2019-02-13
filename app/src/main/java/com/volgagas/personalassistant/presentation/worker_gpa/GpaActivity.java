package com.volgagas.personalassistant.presentation.worker_gpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaPresenter;
import com.volgagas.personalassistant.presentation.worker_gpa.presenter.GpaView;
import com.volgagas.personalassistant.presentation.worker_result.ResultActivity;

import es.dmoral.toasty.Toasty;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class GpaActivity extends BaseActivity implements GpaView {

    private ProgressBar progressBar;
    private TextView tvScanGpa;
    private TextView tvUncorrected;
    private Toolbar toolbar;

    @InjectPresenter
    GpaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        progressBar = findViewById(R.id.progressBar);
        tvScanGpa = findViewById(R.id.tv_scanGpa);
        toolbar = findViewById(R.id.toolbar);
        tvUncorrected = findViewById(R.id.tv_uncorrectedPlace);

        setPermissionToEnableNfc(true);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void sendDataToServer(String data) {
        setPermissionToEnableNfc(false);
        handlerNFC();

        presenter.sendData(data);
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
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        setPermissionToEnableNfc(true);
        handlerNFC();

        Toasty.error(this, error).show();

        tvScanGpa.setText("Приложите карту еще раз");
        tvScanGpa.setVisibility(View.VISIBLE);
    }

    @Override
    public void completed() {
        Toasty.success(this, "Подтверждено о начале работы").show();

        startActivity(new Intent(GpaActivity.this, ResultActivity.class));
        finish();
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
