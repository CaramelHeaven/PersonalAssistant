package com.volgagas.personalassistant.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Message;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:15, 13.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class MainMessangerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;

    public MainMessangerAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false);
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void updateAdapter(List<Message> messages) {
        messageList.clear();
        messageList.addAll(messages);
        notifyDataSetChanged();
    }

    class MessageVH extends RecyclerView.ViewHolder {

        private TextView tvAuthor, tvComment;
        private ImageView ivPhotoUser;

        public MessageVH(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvComment = itemView.findViewById(R.id.tv_message);
            ivPhotoUser = itemView.findViewById(R.id.iv_photo_user);
        }
    }
}
