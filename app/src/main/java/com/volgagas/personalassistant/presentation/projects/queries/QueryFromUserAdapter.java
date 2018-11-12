package com.volgagas.personalassistant.presentation.projects.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:59, 12.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryFromUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UniformRequest> uniformRequests;

    public QueryFromUserAdapter(List<UniformRequest> uniformRequests) {
        this.uniformRequests = uniformRequests;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uniform_request, viewGroup, false);
        return new UniformVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        
    }

    @Override
    public int getItemCount() {
        return uniformRequests.size();
    }

    class UniformVH extends RecyclerView.ViewHolder {

        public UniformVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
