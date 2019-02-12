package com.volgagas.personalassistant.presentation.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.common.HomeModel;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 09:41, 18/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeModel> homeModelList;

    private myOnItemClickListener myOnItemClickListener;

    public HomeAdapter(List<HomeModel> homeModelList) {
        this.homeModelList = homeModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false);

        return new HomeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeVH homeVH = (HomeVH) viewHolder;

        homeVH.tvTitle.setText(homeModelList.get(i).getTitle());
        homeVH.ivImage.setImageDrawable(homeModelList.get(i).getDrawable());
    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    class HomeVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        private ImageView ivImage;
        private CardView cvContainer;

        public HomeVH(@NonNull View itemView) {
            super(itemView);
            cvContainer = itemView.findViewById(R.id.cv_container);
            tvTitle = itemView.findViewById(R.id.tv_option);
            ivImage = itemView.findViewById(R.id.iv_image);

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
