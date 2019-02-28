package com.volgagas.personalassistant.presentation.order_purchase_requestion_more;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:59, 28/02/2019.
 */
public class PurchaseRequisitionMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserSubOrder> list;

    public PurchaseRequisitionMoreAdapter(List<UserSubOrder> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_purchase_requisition_more,
                viewGroup, false);
        return new UserSubOrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAdapter(List<UserSubOrder> userSubOrders) {
        list = userSubOrders;
        notifyDataSetChanged();
    }

    class UserSubOrderVH extends RecyclerView.ViewHolder {

        public UserSubOrderVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
