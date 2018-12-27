package com.volgagas.personalassistant.presentation.order_purchase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order, viewGroup, false);

        return new OrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OrderVH orderVH = (OrderVH) viewHolder;

        orderVH.tvTitle.setText(data.get(i).getTitle());
        orderVH.tvDescription.setText(data.get(i).getDescription());

        if (data.get(i).isStatus()) {
            orderVH.tvStatus.setText("Статус: Доставлено");
            orderVH.ivImage.setImageDrawable(orderVH.ivImage.getContext().getDrawable(R.drawable.ic_box_unpacked));
        } else {
            orderVH.tvStatus.setText("Статус: В процессе");
            orderVH.ivImage.setImageDrawable(orderVH.ivImage.getContext().getDrawable(R.drawable.ic_box_packed));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateAdapter(List<Order> items) {
        data = items;
    }

    class OrderVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvStatus, tvDescription;
        ImageView ivImage;

        public OrderVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
