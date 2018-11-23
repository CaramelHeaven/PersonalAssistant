package com.volgagas.personalassistant.utils.item_touch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.volgagas.personalassistant.presentation.kiosk_added_tasks.KioskAddedTaskAdapter;

/**
 * Created by CaramelHeaven on 11:28, 23.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ItemTouchAdapterKioskAdded extends ItemTouchHelper.Callback {

    private final KioskAddedTaskAdapter adapter;

    public ItemTouchAdapterKioskAdded(KioskAddedTaskAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        KioskAddedTaskAdapter.TaskVH taskVH = (KioskAddedTaskAdapter.TaskVH) viewHolder;
        taskVH.itemView.animate()
                .translationX(0)
                .start();
        adapter.onItemDismiss(taskVH.getAdapterPosition());
    }
}
