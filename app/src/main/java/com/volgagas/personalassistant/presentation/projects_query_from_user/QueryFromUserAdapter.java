package com.volgagas.personalassistant.presentation.projects_query_from_user;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:24, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryFromUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UniformRequest> uniformRequests;

    private myOnItemClickListener myOnItemClickListener;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        UniformVH uniformVH = (UniformVH) viewHolder;

        uniformVH.tvDescription.setText(Html.fromHtml(uniformRequests.get(position).getDescription()));
        uniformVH.tvTitle.setText(uniformRequests.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return uniformRequests.size();
    }

    public void updateAdapter(List<UniformRequest> items) {
        uniformRequests = items;
        notifyDataSetChanged();
    }

    public UniformRequest getItemByPosition(int pos) {
        return uniformRequests.get(pos);
    }

    class UniformVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvDescription;
        private CardView cvContainer;

        public UniformVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvContainer = itemView.findViewById(R.id.cv_container);
            cvContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
