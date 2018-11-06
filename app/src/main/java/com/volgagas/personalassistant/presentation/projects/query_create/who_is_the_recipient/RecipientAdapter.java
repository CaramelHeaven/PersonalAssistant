package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:04, 06.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> userList;

    public RecipientAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipient_worker, viewGroup, false);
        return new RecipientVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RecipientVH recipientVH = (RecipientVH) viewHolder;
        recipientVH.tvPosition.setText(userList.get(i).getPosition());
        recipientVH.tvName.setText(userList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateAdapter(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    private class RecipientVH extends RecyclerView.ViewHolder {

        private ImageView ivPhoto;
        private TextView tvName, tvPosition;

        public RecipientVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_image_worker);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }
    }
}
