package com.volgagas.personalassistant.presentation.worker_result;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.utils.callbacks.OnResultItemClick;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:43, 06/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTask> subTaskList;

    private OnResultItemClick onResultItemClick;

    public ResultAdapter(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result, viewGroup, false);

        return new SubTaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return subTaskList.size();
    }

    public void updateAdapter(List<SubTask> subTasks) {
        subTaskList.clear();
        subTaskList.addAll(subTasks);
    }

    class SubTaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDescription, tvEmptyPictures;
        CardView cardView;
        FloatingActionButton fabCamera;
        RelativeLayout rlStatus;

        private boolean status = false;

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
            rlStatus = itemView.findViewById(R.id.rl_container_status);
            tvDescription = itemView.findViewById(R.id.tv_description);
            fabCamera = itemView.findViewById(R.id.fab_make_photo);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(this);
            fabCamera.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof FloatingActionButton) {
                onResultItemClick.makePhotoClick(getAdapterPosition());
            } else {
                if (status) {
                    status = false;
                    onResultItemClick.onClick(getAdapterPosition(), rlStatus, status);
                } else {
                    status = true;
                    onResultItemClick.onClick(getAdapterPosition(), rlStatus, status);
                }
            }
        }
    }

    public void setOnResultItemClick(OnResultItemClick onResultItemClick) {
        this.onResultItemClick = onResultItemClick;
    }
}
