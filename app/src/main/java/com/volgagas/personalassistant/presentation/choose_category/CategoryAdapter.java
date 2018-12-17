package com.volgagas.personalassistant.presentation.choose_category;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.QueryTemplate;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:29, 14.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QueryTemplate> queryTemplates;
    private myOnItemClickListener myOnItemClickListener;

    public CategoryAdapter(List<QueryTemplate> queryTemplates) {
        this.queryTemplates = queryTemplates;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);

        return new TemplateVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TemplateVH templateVH = (TemplateVH) viewHolder;

        templateVH.tvTitle.setText(queryTemplates.get(i).getTitle());
    }

    public void updateAdapter(List<QueryTemplate> values) {
        queryTemplates = values;
        notifyDataSetChanged();
    }

    public QueryTemplate getItemByPosition(int position) {
        return queryTemplates.get(position);
    }

    @Override
    public int getItemCount() {
        return queryTemplates.size();
    }

    class TemplateVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private CardView cardView;

        public TemplateVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.cardView);
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
}
