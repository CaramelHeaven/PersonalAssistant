package com.volgagas.personalassistant.presentation.order_purchase_order_more;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.ServerSubOrder;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:45, 28/02/2019.
 */
public class PurchaseOrderMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ServerSubOrder> list;

    public PurchaseOrderMoreAdapter(List<ServerSubOrder> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_purchase_order_more,
                viewGroup, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    public void updateAdapter(List<ServerSubOrder> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ServerSubOrderVH extends RecyclerView.ViewHolder {

        public ServerSubOrderVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
