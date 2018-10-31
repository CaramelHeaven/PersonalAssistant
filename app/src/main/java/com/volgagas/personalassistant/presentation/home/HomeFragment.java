package com.volgagas.personalassistant.presentation.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.home.presenter.HomePresenter;
import com.volgagas.personalassistant.presentation.home.presenter.HomeView;

public class HomeFragment extends BaseFragment implements HomeView {

    private ImageView ivUserImage;
    private TextView tvName, tvPosition, tvCompletedTasks, tvCurrentTasks;

    @ProvidePresenter
    HomePresenter provideHomePresenter() {
        return new HomePresenter();
    }

    @InjectPresenter
    HomePresenter presenter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvCompletedTasks = null;
        tvCurrentTasks = null;
        tvName = null;
        tvPosition = null;
        ivUserImage = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
