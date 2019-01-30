package com.volgagas.personalassistant.presentation.worker_task;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 08:56, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTaskViewer> subTasks;
    private myOnItemClickListener myOnItemClickListener;

    public TaskAdapter(List<SubTaskViewer> subTaskViewers) {
        this.subTasks = subTaskViewers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Timber.d("for example: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_task, viewGroup, false);
        return new SubTaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SubTaskVH subTaskVH = (SubTaskVH) viewHolder;
        subTaskVH.tvDescription.setText(subTasks.get(position).getDescription());
        subTaskVH.tvName.setText(subTasks.get(position).getWorkerName());

        if (subTasks.get(position).getState().equals("Yes")) {
            subTaskVH.ivCheckMark.setVisibility(View.VISIBLE);
            subTaskVH.tvDescription.setPaintFlags(subTaskVH.tvDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else if (subTasks.get(position).getWorkerName().equals(CacheUser.getUser().getName())) {
            subTaskVH.rlContainer.setOnClickListener(v -> myOnItemClickListener.onItemClick(position));
        }

        if (subTasks.get(position).isWorking()) {
            subTaskVH.ivCheckMark.setVisibility(View.VISIBLE);
        } else {
            subTaskVH.ivCheckMark.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    public void updateAdapter(List<SubTaskViewer> list) {
        subTasks = list;
    }

    public void updateItem(int position) {
        subTasks.get(position).setWorking(!subTasks.get(position).isWorking());

        notifyItemChanged(position);
    }

    class SubTaskVH extends RecyclerView.ViewHolder {
        TextView tvDescription, tvName;
        ImageView ivCheckMark;
        RelativeLayout rlContainer;

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvName = itemView.findViewById(R.id.tv_worker_name);
            rlContainer = itemView.findViewById(R.id.rl_container);
            ivCheckMark = itemView.findViewById(R.id.iv_check_mark);
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public List<SubTaskViewer> getSubTasks() {
        return subTasks;
    }

    public List<SubTaskViewer> getSelectedTasks() {
        List<SubTaskViewer> list = new ArrayList<>();
        for (SubTaskViewer subTaskViewer : subTasks) {
            if (subTaskViewer.isWorking()) {
                list.add(subTaskViewer);
            }
        }

        return list;
    }
}
