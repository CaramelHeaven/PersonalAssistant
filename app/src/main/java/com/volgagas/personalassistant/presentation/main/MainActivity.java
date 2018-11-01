package com.volgagas.personalassistant.presentation.main;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.about_user.InfoFragment;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.home.HomeFragment;
import com.volgagas.personalassistant.presentation.main.adapters.PagerProjectsAdapter;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private RelativeLayout rlContainer;
    private ImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private ViewPager vpProjectsContainer;
    private TabLayout tabLayout;
    private FrameLayout fragmentContainer, fragmentTest;

    private PagerProjectsAdapter projectsAdapter;
    private ConstraintSet homeSet, projectsSet, infoSet;

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
        tabLayout = findViewById(R.id.tabLayout);
        fragmentContainer = findViewById(R.id.fragment_container);
        fragmentTest = findViewById(R.id.fragment_container_about);
        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        infoSet = new ConstraintSet();

        projectsAdapter = new PagerProjectsAdapter(getSupportFragmentManager());

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        infoSet.clone(this, R.layout.activity_constraint_about);

        setSupportActionBar(toolbar);

        setBottomNavigation();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                .commit();
    }

    @Override
    protected void sendDataToServer(String data) {

    }

    private void setBottomNavigation() {
        bnvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    Fragment fragmente = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                    if (!fragmente.getTag().equals("HOME")) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                                .commit();
                    }
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    homeSet.applyTo(constraintLayout);
                    break;
                case R.id.action_project:
                    vpProjectsContainer.setAdapter(projectsAdapter);
                    tabLayout.setupWithViewPager(vpProjectsContainer);

                    openProjects();
                    break;
                case R.id.action_info:
                    Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("INFO");

                    if (fragment1 == null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container_about, InfoFragment.newInstance(), "INFO")
                                .commit();
                    }
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    infoSet.applyTo(constraintLayout);
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
