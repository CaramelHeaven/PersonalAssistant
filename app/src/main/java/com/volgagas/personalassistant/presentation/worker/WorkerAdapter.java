package com.volgagas.personalassistant.presentation.worker;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.common.GlobalTask;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 07:51, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class WorkerAdapter<T extends GlobalTask> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private myOnItemClickListener myOnItemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_worker, viewGroup, false);
        return new TaskVH(view);
    }

    protected abstract void updateItems(List<T> items);

    protected abstract T getItemByPosition(int position);

    public class TaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle, tvDescription, tvSubTasks, tvDate, tvTime, tvLocation;
        public CardView cardView;
        public ImageView ivCheckMark;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvSubTasks = itemView.findViewById(R.id.tv_subtasks);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvLocation = itemView.findViewById(R.id.tv_location);
            cardView = itemView.findViewById(R.id.cardView);
            ivCheckMark = itemView.findViewById(R.id.iv_check_mark);
            cardView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    /**
     * Merge subtasks to single line which will be reflect description
     *
     * @param builder  = helper class for manipulate string
     * @param subTasks - list sub tasks where we extract each task and merge them.
     * @return one single line
     */
    protected String toDescription(StringBuilder builder, List<SubTask> subTasks) {
        for (int i = 0; i < subTasks.size(); i++) {
            builder.append(subTasks.get(i).getDescription());
            if (i != subTasks.size() - 2) {
                builder.append(", ");
            } else {
                builder.append(".");
                break;
            }
        }
        return builder.toString();
    }
}
