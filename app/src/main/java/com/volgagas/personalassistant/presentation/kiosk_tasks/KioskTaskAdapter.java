package com.volgagas.personalassistant.presentation.kiosk_tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:38, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Task> taskList;
    private Set<Task> uniqueTasks;

    private myOnItemClickListener myOnItemClickListener;

    public KioskTaskAdapter(List<Task> taskList) {
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
       // taskVH.tvCategory.setText(taskList.get(position).getCategoryId());
        taskVH.tvTaskName.setText(taskList.get(position).getDescription());
    }

    public void updateAdapter(List<Task> models) {
        uniqueTasks.addAll(models);
        taskList.clear();
        taskList.addAll(uniqueTasks);
        notifyDataSetChanged();
    }

    public void filterAdapter(List<Task> filterList) {
        Timber.d("filterSize: " + filterList.size());
        taskList.clear();
        taskList.addAll(filterList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public Task getItemByPosition(int position) {
        return taskList.get(position);
    }

    class TaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTaskName, tvCategory;
        CardView cvContainer;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
            cvContainer = itemView.findViewById(R.id.cardView);
            cvContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
