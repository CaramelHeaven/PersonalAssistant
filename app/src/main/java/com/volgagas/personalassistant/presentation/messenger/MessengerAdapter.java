package com.volgagas.personalassistant.presentation.messenger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.Message;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:03, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class MessengerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;
    private Set<Message> uniqueMessages;

    private static final int TYPE_USUALLY = 0;
    private static final int TYPE_USER_MESSAGE = -1;

    public MessengerAdapter(List<Message> messageList) {
        this.messageList = messageList;
        uniqueMessages = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_USER_MESSAGE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_yourself, viewGroup, false);
            return new MessageYourselfVH(view);
        } else if (viewType == TYPE_USUALLY) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false);
            return new MessageVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (TYPE_USER_MESSAGE == getItemViewType(position)) {
            MessageYourselfVH messageYourselfVH = (MessageYourselfVH) viewHolder;

            messageYourselfVH.tvMessage.setText(messageList.get(position).getMessage());

            if (messageList.get(position).isCompletedSendToServer()) {
                messageYourselfVH.ivTime.setVisibility(View.GONE);
                messageYourselfVH.ivDone.setVisibility(View.VISIBLE);
            } else {
                messageYourselfVH.ivDone.setVisibility(View.GONE);
                messageYourselfVH.ivTime.setVisibility(View.VISIBLE);
            }
        } else {
            MessageVH messageVH = (MessageVH) viewHolder;

            messageVH.tvAuthor.setText(messageList.get(position).getAuthor());
            messageVH.tvMessage.setText(messageList.get(position).getMessage());
        }
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

        notifyItemInserted(messageList.size() - 1);
    }

    public void updateMessage(int pos) {
        messageList.get(pos).setCompletedSendToServer(true);

        notifyItemChanged(pos);
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getAuthor().equals(CacheUser.getUser().getModifiedNormalName())) {
            return TYPE_USER_MESSAGE;
        } else {
            return TYPE_USUALLY;
        }
    }

    class MessageVH extends RecyclerView.ViewHolder {
        private TextView tvAuthor, tvMessage;
        private ImageView ivPhoto;

        public MessageVH(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }

    class MessageYourselfVH extends RecyclerView.ViewHolder {
        private TextView tvMessage;
        private ImageView ivDone, ivTime;


        public MessageYourselfVH(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            ivDone = itemView.findViewById(R.id.iv_done);
            ivTime = itemView.findViewById(R.id.iv_time);
        }
    }
}
