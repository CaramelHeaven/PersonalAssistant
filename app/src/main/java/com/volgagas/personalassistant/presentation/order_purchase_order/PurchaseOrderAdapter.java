package com.volgagas.personalassistant.presentation.order_purchase_order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:14, 28/02/2019.
 */
public class PurchaseOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ServerOrder> serverOrders;

    private myOnItemClickListener myOnItemClickListener;

    public PurchaseOrderAdapter(List<ServerOrder> serverOrders) {
        this.serverOrders = serverOrders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_purchase_order,
                viewGroup, false);

        return new ServerOrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ServerOrderVH serverOrderVH = (ServerOrderVH) viewHolder;

        serverOrderVH.tvName.setText(serverOrders.get(i).getPurchaseOrderName());
    }

    @Override
    public int getItemCount() {
        return serverOrders.size();
    }

    public void updateAdapter(List<ServerOrder> serverOrders) {
        this.serverOrders = serverOrders;
        notifyDataSetChanged();
    }

    public String getOrderId(int pos) {
        return serverOrders.get(pos).getPurchaseOrderNumber();
    }

    class ServerOrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvAddress;
        RelativeLayout rlContainer;

        public ServerOrderVH(@NonNull View itemView) {
            super(itemView);
            rlContainer = itemView.findViewById(R.id.rl_container);
            tvName = itemView.findViewById(R.id.tv_title);

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
