package com.volgagas.personalassistant.presentation.query_create;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseGodActivity;
import com.volgagas.personalassistant.presentation.query_create.presenter.QueryCreatePresenter;
import com.volgagas.personalassistant.presentation.query_create.presenter.QueryCreateView;
import com.volgagas.personalassistant.utils.views.ControlledSwipeViewPager;

import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryCreateActivity extends BaseGodActivity implements QueryCreateView {

    private ControlledSwipeViewPager vpContainer;
    private Toolbar toolbar;

    private QueryCreateAdapter adapterQuery;

    @InjectPresenter
    QueryCreatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_request_create);
        vpContainer = findViewById(R.id.vp_container);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            if (vpContainer.getCurrentItem() == 1) {
                vpContainer.setCurrentItem(0);
            } else {
                finish();
            }
        });

        adapterQuery = new QueryCreateAdapter(getSupportFragmentManager());

        vpContainer.setAdapter(adapterQuery);

        vpContainer.enableScroll(false);

        vpContainer.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    vpContainer.enableScroll(true);
                } else {
                    vpContainer.enableScroll(false);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Timber.d("called onBackPressed");
        super.onBackPressed();
    }

    @Override
    public boolean onNavigateUp() {
        Timber.d("called onVaginateUp");
        onBackPressed();
        return super.onNavigateUp();
    }

    @Override
    public void showNextPage() {
        vpContainer.setCurrentItem(1);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
