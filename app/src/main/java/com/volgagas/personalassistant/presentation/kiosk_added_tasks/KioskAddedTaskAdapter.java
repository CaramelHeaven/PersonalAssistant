package com.volgagas.personalassistant.presentation.kiosk_added_tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.kiosk_tasks.KioskTaskAdapter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 9:59, 23.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskAddedTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Task> taskList;
    private Set<Task> uniqueTasks;

    public KioskAddedTaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
        uniqueTasks = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kiosk_task, viewGroup, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TaskVH taskVH = (TaskVH) viewHolder;
        taskVH.tvTaskName.setText(taskList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void addItem(Task model) {
        uniqueTasks.add(model);
        taskList.clear();
        taskList.addAll(uniqueTasks);
        notifyDataSetChanged();
    }

    public void onItemDismiss(int position) {
        uniqueTasks.remove(taskList.get(position));
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public class TaskVH extends RecyclerView.ViewHolder {
        TextView tvTaskName, tvCategory;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}
