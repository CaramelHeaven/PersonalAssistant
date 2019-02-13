package com.volgagas.personalassistant.presentation.worker_today_new;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.utils.callbacks.OnTaskItemClickListener;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;
import com.volgagas.personalassistant.utils.views.sticky_header.StickyRecyclerHeadersAdapter;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:48, 23/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TodayNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private List<Task> items;
    private OnTaskItemClickListener onTaskItemClickListener;

    public TodayNewAdapter(List<Task> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_worker_new,
                viewGroup, false);

        return new TaskVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_worker_new_header, parent, false);

        return new HeaderTaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TaskVH taskVH = (TaskVH) viewHolder;

        taskVH.tvTitle.setText(items.get(i).getDescription());
        taskVH.tvTime.setText("Начало в " + items.get(i).getStartTime());
        taskVH.tvLocation.setText(items.get(i).getGpa());

        taskVH.tvDescription.setText(toDescription(new StringBuilder(), items.get(i).getSubTasks()));
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(getItem(position).getDayOfMonth());
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeaderTaskVH headerTaskVH = (HeaderTaskVH) holder;

        if (items.get(position).getStartDate().equals("Сегодня")) {
            headerTaskVH.cvContainer.setCardBackgroundColor(Color.parseColor("#f57c00"));
            headerTaskVH.tvDayInMonth.setTextColor(Color.parseColor("#FFFFFF"));
            headerTaskVH.tvDayInWeek.setTextColor(Color.parseColor("#f57c00"));
        } else {
            headerTaskVH.cvContainer.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            headerTaskVH.tvDayInMonth.setTextColor(Color.parseColor("#000000"));
            headerTaskVH.tvDayInWeek.setTextColor(Color.parseColor("#CCC6C4"));
        }

        headerTaskVH.tvDayInWeek.setText(items.get(position).getDayOfWeek());
        headerTaskVH.tvDayInMonth.setText(items.get(position).getDayOfMonth());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Task getItem(int position) {
        return items.get(position);
    }

    public void updateItems(List<Task> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    class TaskVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvTime, tvLocation, tvDescription;
        private CardView cvContainer;

        public TaskVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvDescription = itemView.findViewById(R.id.tv_description);
            cvContainer = itemView.findViewById(R.id.cv_container);

            cvContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            onTaskItemClickListener.onClick(getAdapterPosition());
        }
    }

    class HeaderTaskVH extends RecyclerView.ViewHolder {
        private TextView tvDayInMonth, tvDayInWeek;
        private CardView cvContainer;

        public HeaderTaskVH(@NonNull View itemView) {
            super(itemView);
            tvDayInMonth = itemView.findViewById(R.id.tv_day_in_month);
            tvDayInWeek = itemView.findViewById(R.id.tv_day_in_week);
            cvContainer = itemView.findViewById(R.id.cv_container);
        }
    }

    private String toDescription(StringBuilder builder, List<SubTask> subTasks) {
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

    public void setOnTaskItemClickListener(OnTaskItemClickListener onTaskItemClickListener) {
        this.onTaskItemClickListener = onTaskItemClickListener;
    }
}
