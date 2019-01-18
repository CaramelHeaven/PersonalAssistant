package com.volgagas.personalassistant.presentation.worker_today;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.worker.WorkerAdapter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 08:00, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TodayAdapter extends WorkerAdapter<Task> {

    private List<Task> taskList;
    private Set<Task> uniqueTasks;

    public TodayAdapter(List<Task> taskList) {
        this.taskList = taskList;
        uniqueTasks = new LinkedHashSet<>();
    }

    @Override
    protected void updateItems(List<Task> items) {
        uniqueTasks.addAll(items);
        taskList.clear();
        taskList.addAll(uniqueTasks);

        notifyDataSetChanged();
    }

    @Override
    protected Task getItemByPosition(int position) {
        return taskList.get(position);
    }

    public void clear() {
        uniqueTasks.clear();
        taskList.clear();

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TaskVH taskVH = (TaskVH) viewHolder;

        Timber.d("check task: " + taskList.get(position).toString());

        taskVH.tvTitle.setText("№" + convertIdTask(taskList.get(position).getIdTask()) + " " + taskList.get(position).getDescription());

        taskVH.tvSubTasks.setText(String.valueOf(taskList.get(position).getSubTasks().size() + " подзадачи"));
        taskVH.tvDescription.setText(toDescription(new StringBuilder(), taskList.get(position).getSubTasks()));
        taskVH.tvLocation.setText(taskList.get(position).getGpa());

        //set below icons text
        if (!taskList.get(position).getStartDate().equals("Сегодня")) {
            taskVH.tvDate.setText(taskList.get(position).getStartDate());
            taskVH.tvTime.setText(taskList.get(position).getStartTime());
        } else {
            taskVH.tvDate.setText("Сегодня");
            taskVH.tvTime.setText(taskList.get(position).getStartTime());
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    private String convertIdTask(String idTask) {
        return idTask.replaceAll("[^0-9]", "");
    }
}
