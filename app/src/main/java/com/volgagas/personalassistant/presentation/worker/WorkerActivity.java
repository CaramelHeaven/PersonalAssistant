package com.volgagas.personalassistant.presentation.worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerPresenter;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerView;
import com.volgagas.personalassistant.presentation.worker_camera.CameraActivity;
import com.volgagas.personalassistant.presentation.worker_result.ResultActivity;

public class WorkerActivity extends MvpAppCompatActivity implements WorkerView {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    private WorkerPagerAdapter adapter;

    @InjectPresenter
    WorkerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.vp_container);
        tabLayout = findViewById(R.id.view_pager_tab);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        adapter = new WorkerPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
