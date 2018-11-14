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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:18, 14.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientAddedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> userList;
    private Set<User> uniqueUsers;

    public RecipientAddedAdapter(List<User> userList) {
        this.userList = userList;
        uniqueUsers = new LinkedHashSet<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chip_worker, viewGroup, false);
        final UserVH userVH = new UserVH(view);
        return userVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserVH userVH = (UserVH) viewHolder;
        userVH.tvName.setText(userList.get(i).getName());

        if (userList.get(i).getUserImage() != null) {
            byte[] data = Base64.decode(userList.get(i).getUserImage().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight() - 30);
            Glide.with(userVH.ivPhoto.getContext())
                    .load(croppedBmp)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(userVH.ivPhoto);
        } else {
            Glide.with(userVH.ivPhoto.getContext())
                    .clear(userVH.ivPhoto);
            userVH.ivPhoto.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void updateAdapter(List<User> users) {
        Timber.d("UPDATE ADAPTER ADDE");
        uniqueUsers.addAll(users);
        userList.clear();
        userList.addAll(uniqueUsers);
        notifyDataSetChanged();
    }

    class UserVH extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private TextView tvName;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo_user);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
