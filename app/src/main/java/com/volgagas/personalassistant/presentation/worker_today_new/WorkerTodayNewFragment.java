package com.volgagas.personalassistant.presentation.worker_today_new;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewPresenter;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewView;
import com.volgagas.personalassistant.utils.views.sticky_header.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 11:48, 23/01/2019.
 */
public class WorkerTodayNewFragment extends BaseFragment implements WorkerTodayNewView {
    private RecyclerView recyclerView;

    @InjectPresenter
    WorkerTodayNewPresenter presenter;

    public static WorkerTodayNewFragment newInstance() {

        Bundle args = new Bundle();

        WorkerTodayNewFragment fragment = new WorkerTodayNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker_today_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);

        List<String> kek = new ArrayList<>();
        kek.add("Ararara");
        kek.add("Abbbbb");
        kek.add("Ablkclvc");
        kek.add("Avlkclv");
        kek.add("Blckvlx");
        kek.add("Borewkro");
        kek.add("Bkjklvx");
        kek.add("Bckjvxclv");
        kek.add("Bckjvxclv");
        kek.add("Bckjvxclv");
        kek.add("Bckjvxclv");
        kek.add("Bckjvxclv");
        kek.add("Bckjvxclv");
        kek.add("Cckjvxclv");
        kek.add("Cckjvxclv");
        kek.add("Cckjvxclv");
        kek.add("Cckjvxclv");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        TodayNewAdapter adapter = new TodayNewAdapter(kek);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));
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


