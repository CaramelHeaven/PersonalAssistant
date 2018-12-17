package com.volgagas.personalassistant.presentation.worker_task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.worker_task.presenter.TaskPresenter;
import com.volgagas.personalassistant.presentation.worker_task.presenter.TaskView;

import java.util.List;

/**
 * Created by CaramelHeaven on 08:51, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskDialogFragment extends MvpAppCompatDialogFragment implements TaskView<SubTaskViewer> {

    private DisplayMetrics displayMetrics;
    private Task task;
    private TaskAdapter adapter;

    private RecyclerView recyclerView;
    private CardView cvToday, cvHistory;
    private TextView tvTitle, tvError;
    private Button btnCancel, btnAccept, btnReconnect, btnExit;
    private ProgressBar progressBar;

    @InjectPresenter
    TaskPresenter presenter;

    @ProvidePresenter
    TaskPresenter provideTaskDialogPresenter() {
        return new TaskPresenter(getArguments().getParcelable("TASK_DESCRIPTION"),
                getArguments().getString("STATUS"));
    }

    public static TaskDialogFragment newInstance(Task task, String type) {

        Bundle args = new Bundle();
        args.putParcelable("TASK_DESCRIPTION", task);
        args.putString("STATUS", type);

        TaskDialogFragment fragment = new TaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        tvTitle = view.findViewById(R.id.tv_title);
        btnAccept = view.findViewById(R.id.btn_accept);
        cvToday = view.findViewById(R.id.cv_container_today);
        cvHistory = view.findViewById(R.id.cv_container_history);
        btnExit = view.findViewById(R.id.btn_exit);
        btnCancel = view.findViewById(R.id.btn_cancel);
        progressBar = view.findViewById(R.id.progressBar);
        tvError = view.findViewById(R.id.tv_error);
        btnReconnect = view.findViewById(R.id.btn_reconnect);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showItems(List<SubTaskViewer> subTasks) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
