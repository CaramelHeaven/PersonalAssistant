package com.volgagas.personalassistant.presentation.about_user_certificates;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.presentation.about_user_certificates.presenter.CertificatesPresenter;
import com.volgagas.personalassistant.presentation.about_user_certificates.presenter.CertificatesView;

import java.util.ArrayList;
import java.util.List;

public class CertificatesActivity extends MvpAppCompatActivity implements CertificatesView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView ivEmptyData;
    private TextView tvEmptyData;

    private CertificatesAdapter adapter;

    @InjectPresenter
    CertificatesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        tvEmptyData = findViewById(R.id.tv_empty_tasks);
        ivEmptyData = findViewById(R.id.iv_empty_tasks);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new CertificatesAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showItems(List<PersonCertificates> values) {
        if (values.size() > 0) {
            adapter.updateAdapter(values);
        } else {
            ivEmptyData.setVisibility(View.VISIBLE);
            tvEmptyData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
