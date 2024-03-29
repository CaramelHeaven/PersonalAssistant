package com.volgagas.personalassistant.presentation.worker_result;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.utils.callbacks.OnResultItemClick;

import java.io.File;
import java.util.List;

import timber.log.Timber;

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

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SubTaskVH subTaskVH = (SubTaskVH) viewHolder;

        subTaskVH.tvDescription.setText(subTaskList.get(i).getDescription());

        if (subTaskList.get(i).isStateBox()) {
            subTaskVH.rlStatus.setBackgroundColor(subTaskVH.rlStatus.getContext()
                    .getResources().getColor(R.color.colorPrimary));
        } else {
            subTaskVH.rlStatus.setBackgroundColor(subTaskVH.rlStatus.getContext()
                    .getResources().getColor(R.color.colorBackgroundStatusNeutral));
        }

        if (subTaskList.get(i).getFilePath() != null && subTaskList.get(i).getFilePath().length() > 0) {
            Glide.with(subTaskVH.ivImage.getContext())
                    .load(new File(subTaskList.get(i).getFilePath()))
                    .into(subTaskVH.ivImage);

            subTaskVH.fabCamera.setVisibility(View.GONE);
            subTaskVH.btnCancel.setVisibility(View.VISIBLE);
        } else {
            Glide.with(subTaskVH.ivImage.getContext())
                    .clear(subTaskVH.ivImage);

            subTaskVH.fabCamera.setVisibility(View.VISIBLE);
            subTaskVH.btnCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return subTaskList.size();
    }

    public SubTask getItemByPos(int pos) {
        return subTaskList.get(pos);
    }

    public void updateAdapter(List<SubTask> subTasks) {
        subTaskList.clear();
        subTaskList.addAll(subTasks);
    }

    public void updatePicture(int position, String filePath) {
        subTaskList.get(position).setFilePath(filePath);

        notifyItemChanged(position);
    }

    public void clearPicture(int position) {
        Timber.d("checking path file: " + subTaskList.get(position).getFilePath());
        subTaskList.get(position).setFilePath("");
        Timber.d("file after: " + subTaskList.get(position).getFilePath());

        notifyItemChanged(position);
    }

    public void updateStateSubTask(int position, boolean status) {
        Timber.d("CHECK STATUS: " + status);
        subTaskList.get(position).setStateBox(status);
        notifyItemChanged(position);
    }

    class SubTaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDescription;
        CardView cardView;
        FloatingActionButton fabCamera;
        RelativeLayout rlStatus;
        ImageView ivImage;
        Button btnCancel;

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
            rlStatus = itemView.findViewById(R.id.rl_container_status);
            tvDescription = itemView.findViewById(R.id.tv_description);
            fabCamera = itemView.findViewById(R.id.fab_make_photo);
            cardView = itemView.findViewById(R.id.cardView);
            ivImage = itemView.findViewById(R.id.iv_image);
            btnCancel = itemView.findViewById(R.id.btn_cancel);

            cardView.setOnClickListener(this);
            fabCamera.setOnClickListener(this);

            btnCancel.setOnClickListener(v ->
                    onResultItemClick.makeClearPicture(getAdapterPosition()));
        }

        @Override
        public void onClick(View v) {
            if (v instanceof FloatingActionButton) {
                if (ivImage.getDrawable() == null) {
                    onResultItemClick.makePhotoClick(getAdapterPosition());
                }
            } else {
                if (((ColorDrawable) rlStatus.getBackground()).getColor() ==
                        rlStatus.getContext().getResources().getColor(R.color.colorPrimary)) {
                    onResultItemClick.onClick(getAdapterPosition(), false);
                } else {
                    onResultItemClick.onClick(getAdapterPosition(), true);
                }
            }
        }
    }

    public void setOnResultItemClick(OnResultItemClick onResultItemClick) {
        this.onResultItemClick = onResultItemClick;
    }
}
