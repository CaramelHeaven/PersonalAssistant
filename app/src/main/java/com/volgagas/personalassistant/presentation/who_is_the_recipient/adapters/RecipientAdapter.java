package com.volgagas.personalassistant.presentation.who_is_the_recipient.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:04, 06.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> userList;
    private List<User> addedUsers;
    private myOnItemClickListener myOnItemClickListener;
    private myOnCustomItemClickListener myOnCustomItemClickListener;

    private static final int TYPE_ADDED = 0;
    private static final int TYPE_ALL = 1;

    public RecipientAdapter(List<User> userList) {
        this.userList = userList;
        addedUsers = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (getItemViewType(position) == TYPE_ADDED) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipient_worker_container, viewGroup, false);
            return new UserAddedVH(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipient_worker, viewGroup, false);
            Timber.d("RECIPIENT CALLED");
            return new RecipientVH(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Timber.d("getItemViewType by position: " + position);
        Timber.d("user list: " + userList.size());
        if (position == TYPE_ADDED) {
            UserAddedVH userAddedVH = (UserAddedVH) viewHolder;
            userAddedVH.addedAdapter.updateAdapter(addedUsers);
        } else {
            Timber.d("CALL SIMPLE USER");
            int pos = position - 1;
            RecipientVH recipientVH = (RecipientVH) viewHolder;
            recipientVH.tvPosition.setText(userList.get(pos).getPosition());
            recipientVH.tvName.setText(userList.get(pos).getName());

            if (userList.get(pos).getUserImage() != null) {
                byte[] data = Base64.decode(userList.get(pos).getUserImage().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
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


    }

    @Override
    public int getItemCount() {
        return userList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ADDED) {
            return TYPE_ADDED;
        } else {
            return TYPE_ALL;
        }
    }

    public void updateAdapter(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public User getItemByPosition(int position) {
        return userList.get(--position);
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<User> getAddedUsers() {
        return addedUsers;
    }

    public void updateAddedUsers(User user) {
        addedUsers.add(user);
        notifyItemChanged(TYPE_ADDED);
    }

    private class RecipientVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivPhoto;
        private TextView tvName, tvPosition;
        private RelativeLayout rlContainer;

        public RecipientVH(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_image_worker);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPosition = itemView.findViewById(R.id.tv_position);
            rlContainer = itemView.findViewById(R.id.rl_container);
            rlContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    private class UserAddedVH extends RecyclerView.ViewHolder {

        private RecyclerView rvAddedWorkers;
        private RecipientAddedAdapter addedAdapter;

        public UserAddedVH(@NonNull View itemView) {
            super(itemView);
            rvAddedWorkers = itemView.findViewById(R.id.rv_added_workers);

            rvAddedWorkers.setHasFixedSize(true);
            rvAddedWorkers.setLayoutManager(new GridLayoutManager(rvAddedWorkers.getContext(), 2));

            addedAdapter = new RecipientAddedAdapter(new ArrayList<>());
            rvAddedWorkers.setAdapter(addedAdapter);

            addedAdapter.setMyOnItemClickListener(position -> {
                Timber.d("remove");
                addedAdapter.removeUser(addedAdapter.getUserByPosition(position));
            });
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnCustomItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnCustomItemClickListener myOnCustomItemClickListener) {
        this.myOnCustomItemClickListener = myOnCustomItemClickListener;
    }
}
