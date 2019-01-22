package com.volgagas.personalassistant.presentation.worker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseGodActivity;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerPresenter;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerView;

public class WorkerActivity extends BaseGodActivity implements WorkerView {

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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
