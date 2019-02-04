package com.volgagas.personalassistant.presentation.worker_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.common.GlobalTask;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.worker_choose_action.ChooseActionActivity;
import com.volgagas.personalassistant.presentation.worker_task.presenter.TaskPresenter;
import com.volgagas.personalassistant.presentation.worker_task.presenter.TaskView;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;
import com.volgagas.personalassistant.utils.manager.TaskStartedManager;

import java.util.List;

/**
 * Created by CaramelHeaven on 08:51, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskDialogFragment extends MvpAppCompatDialogFragment implements TaskView<SubTaskViewer> {

    private DisplayMetrics displayMetrics;
    private TaskAdapter adapter;

    private RecyclerView recyclerView;
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

    public static TaskDialogFragment newInstance(GlobalTask task, String type) {

        Bundle args = new Bundle();
        args.putParcelable("TASK_DESCRIPTION", task);
        args.putString("STATUS", type);

        TaskDialogFragment fragment = new TaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TaskDialogFragment newInstance(TaskHistory task, String type) {

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
        btnExit = view.findViewById(R.id.btn_exit);
        btnCancel = view.findViewById(R.id.btn_cancel);
        progressBar = view.findViewById(R.id.progressBar);
        tvError = view.findViewById(R.id.tv_error);
        btnReconnect = view.findViewById(R.id.btn_reconnect);


        if (presenter.getGlobalTask() instanceof Task) {
            tvTitle.setText(((Task) presenter.getGlobalTask()).getDescription());
        } else if (presenter.getGlobalTask() instanceof TaskHistory) {
            tvTitle.setText(((TaskHistory) presenter.getGlobalTask()).getDescription());
        }

        switch (presenter.getStatus()) {
            case "TODAY":
                btnCancel.setVisibility(View.VISIBLE);
                btnAccept.setVisibility(View.VISIBLE);
                break;
            case "HISTORY":
                btnExit.setVisibility(View.VISIBLE);
                break;
        }

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        provideButtons();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 280);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        tvTitle = null;
        btnCancel = null;
        btnExit = null;
        btnAccept = null;
        progressBar = null;
        btnReconnect = null;
        tvError = null;
        super.onDestroyView();
    }

    @Override
    public void showItems(List<SubTaskViewer> subTasks) {
        if (subTasks.size() != 0) {
            provideRecyclerAndAdapter(subTasks);
        } else {
            Toast.makeText(getActivity(), "Задач нет", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {
        tvError.setVisibility(View.GONE);
        btnReconnect.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideRecyclerAndAdapter(List<SubTaskViewer> subTasks) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new TaskAdapter(subTasks);
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> adapter.updateItem(position));
    }

    private void provideButtons() {
        btnAccept.setOnClickListener(view -> {
            if (adapter.getSelectedTasks().size() > 0) {
                Intent intent = new Intent(getActivity(), ChooseActionActivity.class);
                intent.putExtra("ACTION", Constants.USUAL);

                if (presenter.getGlobalTask() instanceof Task) {
                    Task task = (Task) presenter.getGlobalTask();
                    TaskContentManager.getInstance().setCurrentTask(task);
                    TaskContentManager.getInstance().setSubTasks(adapter.getSelectedTasks());
                }

                startActivity(intent);
                getDialog().dismiss();
            } else {
                Toast.makeText(getActivity(), "Задачи не выбраны", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dismiss());

        btnExit.setOnClickListener(view -> dismiss());

    }
}
