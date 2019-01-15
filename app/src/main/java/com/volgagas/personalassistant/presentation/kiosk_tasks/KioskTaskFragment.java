package com.volgagas.personalassistant.presentation.kiosk_tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.kiosk_tasks.presenter.KioskTaskPresenter;
import com.volgagas.personalassistant.presentation.kiosk_tasks.presenter.KioskTaskView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.AddedTask;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:10, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskTaskFragment extends BaseFragment implements KioskTaskView<TaskTemplate> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmptyTasks;

    private KioskTaskAdapter adapter;
    private Set<TaskTemplate> filterList;

    @InjectPresenter
    KioskTaskPresenter presenter;

    public static KioskTaskFragment newInstance() {

        Bundle args = new Bundle();

        KioskTaskFragment fragment = new KioskTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kiosk_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress_bar);
        tvEmptyTasks = view.findViewById(R.id.tv_empty_tasks);

        filterList = new LinkedHashSet<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new KioskTaskAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            TaskTemplate task = adapter.getItemByPosition(position);

            GlobalBus.getEventBus().post(new AddedTask(adapter.getItemByPosition(position)));
            adapter.removeByPosition(position);
            filterList.remove(task);

            //successful add task
            Toasty.normal(getActivity(), "Добавлено " + task.getDescription(),
                    getActivity().getDrawable(R.drawable.ic_task)).show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getEventBus().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void filterList(String str) {
        if (!str.isEmpty()) {
            final List<TaskTemplate> filtering = new ArrayList<>();
            for (TaskTemplate val : filterList) {
                if (val.getDescription().toLowerCase().contains(str)) {
                    filtering.add(val);
                }
            }
            adapter.filterAdapter(filtering);
        } else {
            adapter.updateAdapter(new ArrayList<>(filterList));
        }
    }

    /**
     * Add task again from AddedFragment
     *
     * @param taskTemplate - task from kiosk added screen for add again in the common list
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void addedTaskAgainToList(TaskTemplate taskTemplate) {
        adapter.addItem(taskTemplate);

        filterList.add(taskTemplate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        progressBar = null;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

        if (tvEmptyTasks.getVisibility() == View.VISIBLE) {
            tvEmptyTasks.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<TaskTemplate> models) {
        if (models.size() != 0) {
            filterList.clear();
            filterList.addAll(models);

            adapter.updateAdapter(models);
        } else {
            tvEmptyTasks.setVisibility(View.VISIBLE);
        }
    }
}
