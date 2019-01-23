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
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewPresenter;
import com.volgagas.personalassistant.presentation.worker_today_new.presenter.WorkerTodayNewView;
import com.volgagas.personalassistant.utils.views.sticky_header.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 11:48, 23/01/2019.
 */
public class WorkerTodayNewFragment extends BaseFragment implements WorkerTodayNewView<Task> {

    private TodayNewAdapter adapter;

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

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new TodayNewAdapter(new ArrayList<>());
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

    @Override
    public void showItems(List<Task> models) {
        adapter.updateItems(models);
    }
}


