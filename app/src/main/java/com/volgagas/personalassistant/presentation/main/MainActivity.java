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
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private RelativeLayout rlContainer;
    private ImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private ViewPager vpContainer;
    private TabLayout tabLayout;

    private PagerAdapter pagerAdapter;
    private ConstraintSet homeSet;
    private ConstraintSet projectsSet;
    private ConstraintSet dashboardSet;

    private boolean projectToHomeAnimation = false;
    private int mesure;

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
        vpContainer = findViewById(R.id.vp_container);
        tabLayout = findViewById(R.id.tabLayout);
        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        dashboardSet = new ConstraintSet();

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        dashboardSet.clone(this, R.layout.activity_constraint_dashboard);

        setSupportActionBar(toolbar);

        setBottomNavigation();
        bnvNavigation.setSelectedItemId(R.id.action_home);

        tabLayout.setupWithViewPager(vpContainer);
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
                    openProjects();
                    break;
                case R.id.action_dashboard:
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
