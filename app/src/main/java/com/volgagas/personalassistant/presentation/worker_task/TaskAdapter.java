package com.volgagas.personalassistant.presentation.worker_task;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.SubTaskViewer;

import java.util.List;

/**
 * Created by CaramelHeaven on 08:56, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTaskViewer> subTaskViewers;

    public TaskAdapter(List<SubTaskViewer> subTaskViewers) {
        this.subTaskViewers = subTaskViewers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_task, viewGroup, false);
        return new SubTaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SubTaskVH subTaskVH = (SubTaskVH) viewHolder;
        subTaskVH.tvDescription.setText(subTaskViewers.get(position).getDescription());
        subTaskVH.tvName.setText(subTaskViewers.get(position).getWorkerName());

        if (subTaskViewers.get(position).getState().equals("Yes")) {
            subTaskVH.ivCheckMark.setVisibility(View.VISIBLE);
            subTaskVH.tvDescription.setPaintFlags(subTaskVH.tvDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return subTaskViewers.size();
    }

    public void updateAdapter(List<SubTaskViewer> list) {
        subTaskViewers = list;
    }

    class SubTaskVH extends RecyclerView.ViewHolder {
        TextView tvDescription, tvName;
        ImageView ivCheckMark;

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvName = itemView.findViewById(R.id.tv_worker_name);
            ivCheckMark = itemView.findViewById(R.id.iv_check_mark);
        }
    }
}
