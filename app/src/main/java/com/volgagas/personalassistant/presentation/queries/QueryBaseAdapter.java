package com.volgagas.personalassistant.presentation.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:29, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryBaseAdapter<T extends QueryToUser, T1 extends UniformRequest> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> listAdapter;
    private List<T> uniformToUser;
    private List<T1> uniformFromUser;

    private myOnItemClickListener myOnItemClickListener;

    public QueryBaseAdapter(List<String> listAdapter) {
        this.listAdapter = listAdapter;
        uniformFromUser = new ArrayList<>();
        uniformToUser = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_expandable, viewGroup, false);
        return new ExpandableVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ExpandableVH expandableVH = (ExpandableVH) viewHolder;
        expandableVH.tvName.setText(listAdapter.get(position));

        if (position == 0) {
            expandableVH.adapter.updateAdapter(uniformToUser);
            expandableVH.expandableLinearLayout.setExpanded(true);
        } else {
            expandableVH.adapter.updateAdapter(uniformFromUser);
        }
    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }

    public void updateAdapter(List<String> baseList, List<T> uniformToUser, List<T1> uniformRequests) {
        listAdapter.addAll(baseList);

        this.uniformToUser.addAll(uniformToUser);
        uniformFromUser.addAll(uniformRequests);
        notifyDataSetChanged();
    }

    class ExpandableVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerView rvContainer;
        private QueryMiniAdapter adapter;
        private ExpandableLinearLayout expandableLinearLayout;
        private TextView tvName;
        private CardView cvContainer;

        public ExpandableVH(@NonNull View itemView) {
            super(itemView);
            rvContainer = itemView.findViewById(R.id.rv_container);
            expandableLinearLayout = itemView.findViewById(R.id.expandable_container);
            tvName = itemView.findViewById(R.id.tv_name);
            cvContainer = itemView.findViewById(R.id.cv_container);
            cvContainer.setOnClickListener(this::onClick);

            rvContainer.setHasFixedSize(true);
            rvContainer.setLayoutManager(new LinearLayoutManager(rvContainer.getContext(), LinearLayoutManager.VERTICAL, false));

            adapter = new QueryMiniAdapter(new ArrayList<>());
            rvContainer.setAdapter(adapter);

            adapter.setMyOnItemClickListener(position -> {
                myOnItemClickListener.onItemClick(getAdapterPosition());
                Timber.d("click: " + position);
                Timber.d("click: " + position);
            });
        }

        @Override
        public void onClick(View v) {
            // myOnItemClickListener.onItemClick(getAdapterPosition());
            expandableLinearLayout.toggle();
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
