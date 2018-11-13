package com.volgagas.personalassistant.presentation.projects.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.callbacks.myOnCustomItemClickListener;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:59, 12.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryFromUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UniformRequest> data;
    private myOnCustomItemClickListener myOnCustomItemClickListener;

    public QueryFromUserAdapter(List<UniformRequest> uniformRequests) {
        this.data = uniformRequests;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uniform_request, viewGroup, false);
        return new UniformVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        UniformVH uniformVH = (UniformVH) viewHolder;
        uniformVH.tvTitle.setText(data.get(position).getTitle());
        uniformVH.tvDescription.setText(data.get(position).getDescription());

        Timber.d("DATA: " + data.get(position).getPriority());
        if (data.get(position).getPriority().contains(Constants.PRIORITY_HIGH)) {
            uniformVH.cvPriority.setVisibility(View.VISIBLE);
        } else {
            uniformVH.cvPriority.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateAdapter(List<UniformRequest> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    class UniformVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvDescription;
        private CardView cvPriority, cvContainer;

        public UniformVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvPriority = itemView.findViewById(R.id.cv_priority);
            cvContainer = itemView.findViewById(R.id.cv_container);
            cvContainer.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View v) {
            if (v != null) {
                myOnCustomItemClickListener.onItemClick(getAdapterPosition(), v);
            }
        }
    }

    public void setMyOnCustomItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnCustomItemClickListener myOnCustomItemClickListener) {
        this.myOnCustomItemClickListener = myOnCustomItemClickListener;
    }
}
