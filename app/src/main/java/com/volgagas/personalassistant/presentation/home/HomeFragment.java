package com.volgagas.personalassistant.presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.home.presenter.HomePresenter;
import com.volgagas.personalassistant.presentation.home.presenter.HomeView;
import com.volgagas.personalassistant.presentation.query_create.QueryCreateActivity;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class HomeFragment extends BaseFragment implements HomeView {

    private CardView cvCreateTask;

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
        cvCreateTask = view.findViewById(R.id.cv_container);

        cvCreateTask.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QueryCreateActivity.class));
            Timber.d("click");
            Timber.d("click");
        });
//        recyclerView = view.findViewById(R.id.recyclerView);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//
//        adapter = new HomeAdapter(new ArrayList<>(Arrays.asList("Создать заявку", "Пожелание", "Kfkfkf", "lflflf")));
//        recyclerView.setAdapter(adapter);
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
