package com.volgagas.personalassistant.presentation.order_purchase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.models.model.order_purchase.Order;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:33, 25/12/2018.
 */
public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Order> data;

    public OrderAdapter(List<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OrderVH extends RecyclerView.ViewHolder {

        public OrderVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
