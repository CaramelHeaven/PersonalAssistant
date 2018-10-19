package com.volgagas.personalassistant.presentation.projects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.main.PagerAdapter;
import com.volgagas.personalassistant.presentation.projects.presenter.ProjectsPresenter;
import com.volgagas.personalassistant.presentation.projects.presenter.ProjectsView;

public class ProjectsFragment extends BaseFragment implements ProjectsView {

    private ViewPager vpContainer;

    private PagerAdapter pagerAdapter;

    @ProvidePresenter
    ProjectsPresenter provideProjectsPresenter() {
        return new ProjectsPresenter();
    }

    @InjectPresenter
    ProjectsPresenter projectPresenters;

    public static ProjectsFragment newInstance() {

        Bundle args = new Bundle();

        ProjectsFragment fragment = new ProjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vpContainer = view.findViewById(R.id.vp_container);
        vpContainer.setAdapter(pagerAdapter);

        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
