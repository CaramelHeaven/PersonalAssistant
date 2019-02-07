package com.volgagas.personalassistant.presentation.about_user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.Info;
import com.volgagas.personalassistant.presentation.about_user.presenter.InfoPresenter;
import com.volgagas.personalassistant.presentation.about_user.presenter.InfoView;
import com.volgagas.personalassistant.presentation.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.work.impl.model.SystemIdInfoDao;

public class InfoFragment extends BaseFragment implements InfoView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private InfoAdapter adapter;

    @InjectPresenter
    InfoPresenter presenter;

    public static InfoFragment newInstance() {

        Bundle args = new Bundle();

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new InfoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        progressBar = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<Object> objects) {
        adapter.updateAdapter(objects);
    }
}
