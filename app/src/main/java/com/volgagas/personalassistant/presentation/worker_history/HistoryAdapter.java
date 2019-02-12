package com.volgagas.personalassistant.presentation.worker_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.worker.WorkerAdapter;

import java.util.List;

/**
 * Created by CaramelHeaven on 08:41, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class HistoryAdapter extends WorkerAdapter<TaskHistory> {

    private List<TaskHistory> taskList;

    public HistoryAdapter(List<TaskHistory> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TaskVH taskVH = (TaskVH) viewHolder;

        taskVH.tvTitle.setText(taskList.get(position).getDescription());
        taskVH.tvDescription.setText(toDescription(new StringBuilder(), taskList.get(position).getSubTasks()));
        taskVH.tvDate.setText(taskList.get(position).getSubTasks().get(0).getStartDate());

        taskVH.tvSubTasks.setText("Выполнено вами задач: " + taskList.get(position).getSubTasks().size());

        taskVH.tvTime.setText(taskList.get(position).getSubTasks().get(0).getStartTime());
        taskVH.tvLocation.setText(taskList.get(position).getGpa());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    protected void updateItems(List<TaskHistory> items) {
        taskList = items;
        notifyDataSetChanged();
    }

    @Override
    protected TaskHistory getItemByPosition(int position) {
        return taskList.get(position);
    }
}
