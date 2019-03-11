package com.volgagas.personalassistant.utils.item_touch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureAdapter;

/**
 * Created by CaramelHeaven on 18:18, 11/03/2019.
 */
public class ItemTouchAdapterNomenclature extends ItemTouchHelper.Callback {

    private final NomenclatureAdapter adapter;

    public ItemTouchAdapterNomenclature(NomenclatureAdapter adapter) {
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
        NomenclatureAdapter.NomenclatureVH nomenclatureVH = (NomenclatureAdapter.NomenclatureVH) viewHolder;
        nomenclatureVH.itemView.animate()
                .translationX(0)
                .start();

        adapter.onItemDismiss(nomenclatureVH.getAdapterPosition());
    }
}
