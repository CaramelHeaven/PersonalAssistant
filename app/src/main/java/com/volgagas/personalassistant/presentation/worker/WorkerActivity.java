package com.volgagas.personalassistant.presentation.worker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerPresenter;
import com.volgagas.personalassistant.presentation.worker.presenter.WorkerView;

public class WorkerActivity extends MvpAppCompatActivity implements WorkerView {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private WorkerPagerAdapter adapter;

    @InjectPresenter
    WorkerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        viewPager = findViewById(R.id.vp_container);
        tabLayout = findViewById(R.id.view_pager_tab);

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
