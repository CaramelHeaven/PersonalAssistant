package com.volgagas.personalassistant.presentation.order_purchase_requestion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:36, 27/02/2019.
 */
public class PurchaseRequisitionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UserOrder> orders;

    private myOnItemClickListener myOnItemClickListener;

    public PurchaseRequisitionAdapter(List<UserOrder> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_purchase_requisition, viewGroup, false);

        return new UserOrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserOrderVH userOrderVH = (UserOrderVH) viewHolder;

        userOrderVH.tvDescription.setText(orders.get(i).getDescription());
        userOrderVH.tvTitle.setText("Заказ " + orders.get(i).getRequisitionNumber() +
                orders.get(i).getDefaultBusinessName());
        userOrderVH.tvStatus.setText(orders.get(i).getStatus());
    }

    public void updateAdapter(List<UserOrder> orders) {
        this.orders = orders;
        Timber.d("orders: " + orders.size());
        notifyDataSetChanged();
    }

    public String getRequisitionNumber(int pos) {
        return orders.get(pos).getRequisitionNumber();
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class UserOrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvStatus, tvDescription;
        RelativeLayout rlContainer;

        public UserOrderVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvDescription = itemView.findViewById(R.id.tv_description);
            rlContainer = itemView.findViewById(R.id.rl_container);

            rlContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
