package com.volgagas.personalassistant.presentation.worker_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.worker.WorkerAdapter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 08:41, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class HistoryAdapter extends WorkerAdapter<Task> {

    private List<Task> taskList;
    private Set<Task> uniqueTasks;

    public HistoryAdapter(List<Task> taskList) {
        this.taskList = taskList;
        uniqueTasks = new LinkedHashSet<>();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    protected void updateItems(List<Task> items) {

    }

    @Override
    protected Task getItemByPosition(int position) {
        return taskList.get(position);
    }
}
