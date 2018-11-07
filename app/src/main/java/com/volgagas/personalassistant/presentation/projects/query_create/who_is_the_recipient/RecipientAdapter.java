package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

import timber.log.Timber;

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

    public List<User> getUserList() {
        return userList;
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
