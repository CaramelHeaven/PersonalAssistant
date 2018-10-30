package com.volgagas.personalassistant.presentation.main;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.main.adapters.PagerAboutAdapter;
import com.volgagas.personalassistant.presentation.main.adapters.PagerProjectsAdapter;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private RelativeLayout rlContainer;
    private ImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private ViewPager vpProjectsContainer, vpAboutContainer;
    private TabLayout tabLayout, tabLayout2;

    private PagerProjectsAdapter projectsAdapter;
    private PagerAboutAdapter pagerAboutAdapter;
    private ConstraintSet homeSet, projectsSet, dashboardSet;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_home);
        ivUserImage = findViewById(R.id.imageView);
        bnvNavigation = findViewById(R.id.bnv_navigation);
        constraintLayout = findViewById(R.id.constraintLayout);
        vpProjectsContainer = findViewById(R.id.vp_container);
        vpAboutContainer = findViewById(R.id.vp_container2);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout2 = findViewById(R.id.tabLayout2);
        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        dashboardSet = new ConstraintSet();

        projectsAdapter = new PagerProjectsAdapter(getSupportFragmentManager());
        pagerAboutAdapter = new PagerAboutAdapter(getSupportFragmentManager());

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        dashboardSet.clone(this, R.layout.activity_constraint_about);

        setSupportActionBar(toolbar);

        setBottomNavigation();
        bnvNavigation.setSelectedItemId(R.id.action_home);
    }

    @Override
    protected void sendDataToServer(String data) {

    }

    private void setBottomNavigation() {
        bnvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    homeSet.applyTo(constraintLayout);
                    break;
                case R.id.action_project:
                    vpProjectsContainer.setAdapter(projectsAdapter);
                    tabLayout.setupWithViewPager(vpProjectsContainer);
                    openProjects();
                    break;
                case R.id.action_dashboard:
                    vpAboutContainer.setAdapter(pagerAboutAdapter);
                    tabLayout2.setupWithViewPager(vpAboutContainer);
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    dashboardSet.applyTo(constraintLayout);
                    break;
            }
            return true;
        });
    }

    private void openHome() {

    }

    private void openProjects() {
        TransitionManager.beginDelayedTransition(constraintLayout);
        projectsSet.applyTo(constraintLayout);
    }
}
