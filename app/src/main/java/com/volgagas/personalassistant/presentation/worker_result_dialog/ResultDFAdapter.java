package com.volgagas.personalassistant.presentation.worker_result_dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.SubTask;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:55, 31/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ResultDFAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTask> subTaskList;

    public ResultDFAdapter(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ResultVH(LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.item_worker_task, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ResultVH resultVH = (ResultVH) viewHolder;

        resultVH.tvDescription.setText(subTaskList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return subTaskList.size();
    }

    class ResultVH extends RecyclerView.ViewHolder {
        TextView tvDescription;

        public ResultVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
