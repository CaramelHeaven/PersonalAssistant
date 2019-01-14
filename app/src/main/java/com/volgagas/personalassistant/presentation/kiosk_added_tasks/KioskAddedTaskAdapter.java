package com.volgagas.personalassistant.presentation.kiosk_added_tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 9:59, 23.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskAddedTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TaskTemplate> taskList;

    private myOnItemClickListener myOnItemClickListener;

    public KioskAddedTaskAdapter(List<TaskTemplate> taskList) {
        this.taskList = taskList;
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

    public void addItem(TaskTemplate model) {
        taskList.add(model);

        notifyDataSetChanged();
    }

    public void onItemDismiss(int position) {
        myOnItemClickListener.onItemClick(position);
    }

    public void removeItemByPosition(int pos) {
        taskList.remove(pos);

        notifyItemRemoved(pos);
    }

    public TaskTemplate getItemByPosition(int position) {
        return taskList.get(position);
    }

    public class TaskVH extends RecyclerView.ViewHolder {
        TextView tvTaskName, tvCategory;
        ImageButton iBtnRemove;
        ImageView ivArrow;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
            iBtnRemove = itemView.findViewById(R.id.ibtn_remove);
            ivArrow = itemView.findViewById(R.id.iv_arrow);

            ivArrow.setVisibility(View.GONE);
            iBtnRemove.setVisibility(View.VISIBLE);

            iBtnRemove.setOnClickListener(v -> {
                myOnItemClickListener.onItemClick(getAdapterPosition());
            });
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
