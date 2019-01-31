package com.volgagas.personalassistant.presentation.worker_result_dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.models.model.worker.SubTask;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:55, 31/01/2019.
 */
public class ResultDFAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTask> subTaskList;

    public ResultDFAdapter(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return subTaskList.size();
    }

    class ResultVH extends RecyclerView.ViewHolder {

        public ResultVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
