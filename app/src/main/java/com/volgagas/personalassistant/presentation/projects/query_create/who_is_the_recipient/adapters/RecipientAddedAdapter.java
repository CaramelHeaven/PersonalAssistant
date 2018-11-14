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

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipient_worker, viewGroup, false);
        final UserVH userVH = new UserVH(view);
        return userVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserVH userVH = (UserVH) viewHolder;
        userVH.tvPosition.setText(userList.get(i).getPosition());
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

    public void updateAdapter(User user) {
        uniqueUsers.add(user);
        userList.clear();
        userList.addAll(uniqueUsers);
        notifyDataSetChanged();
    }

    class UserVH extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private TextView tvName, tvPosition;
        private RelativeLayout rlContainer;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_image_worker);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPosition = itemView.findViewById(R.id.tv_position);
            rlContainer = itemView.findViewById(R.id.rl_container);
        }
    }
}
