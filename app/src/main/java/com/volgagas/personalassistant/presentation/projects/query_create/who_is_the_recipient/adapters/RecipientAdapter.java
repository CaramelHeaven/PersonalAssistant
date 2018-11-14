package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.utils.callbacks.myOnCustomItemClickListener;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by CaramelHeaven on 14:04, 06.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> userList;
    private myOnItemClickListener myOnItemClickListener;
    private myOnCustomItemClickListener myOnCustomItemClickListener;

    public RecipientAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipient_worker, viewGroup, false);
        final RecipientVH recipientVH = new RecipientVH(view);

        recipientVH.rlContainer.setOnClickListener(v -> {
            myOnCustomItemClickListener.onItemClick(recipientVH.getAdapterPosition(), v);
            // myOnCustomItemClickListener.onItemClick(viewGroup.getId(), v);
        });

        return recipientVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RecipientVH recipientVH = (RecipientVH) viewHolder;
        recipientVH.tvPosition.setText(userList.get(i).getPosition());
        recipientVH.tvName.setText(userList.get(i).getName());

        if (userList.get(i).getUserImage() != null) {
            byte[] data = Base64.decode(userList.get(i).getUserImage().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight() - 30);
            Glide.with(recipientVH.ivPhoto.getContext())
                    .load(croppedBmp)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(recipientVH.ivPhoto);
        } else {
            Glide.with(recipientVH.ivPhoto.getContext())
                    .clear(recipientVH.ivPhoto);
            recipientVH.ivPhoto.setImageDrawable(null);
        }
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

    public User getItemByPosition(int position) {
        return userList.get(position);
    }

    public List<User> getUserList() {
        return userList;
    }

    private class RecipientVH extends RecyclerView.ViewHolder {

        private ImageView ivPhoto;
        private TextView tvName, tvPosition;
        private RelativeLayout rlContainer;

        public RecipientVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_image_worker);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPosition = itemView.findViewById(R.id.tv_position);
            rlContainer = itemView.findViewById(R.id.rl_container);
            // rlContainer.setOnClickListener(this::onClick);
        }

//        @Override
//        public void onClick(View v) {
//            //myOnItemClickListener.onItemClick(getAdapterPosition());
//        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnCustomItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnCustomItemClickListener myOnCustomItemClickListener) {
        this.myOnCustomItemClickListener = myOnCustomItemClickListener;
    }
}
