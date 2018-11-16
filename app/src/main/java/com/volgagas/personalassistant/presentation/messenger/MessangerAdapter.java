package com.volgagas.personalassistant.presentation.messenger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Message;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CaramelHeaven on 13:03, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class MessangerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;
    private Set<Message> uniqueMessages;

    public MessangerAdapter(List<Message> messageList) {
        this.messageList = messageList;
        uniqueMessages = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false);
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MessageVH messageVH = (MessageVH) viewHolder;
        messageVH.tvMessage.setText(messageList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void updateAdapter(List<Message> messages) {
        uniqueMessages.addAll(messages);
        messageList.clear();
        messageList.addAll(uniqueMessages);
        notifyDataSetChanged();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void addMessage(Message message) {
        uniqueMessages.add(message);
        messageList.clear();
        messageList.addAll(uniqueMessages);
        notifyDataSetChanged();
    }

    class MessageVH extends RecyclerView.ViewHolder {

        private TextView tvAuthor, tvMessage;

        public MessageVH(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }
}
