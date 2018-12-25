package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:35, 25/12/2018.
 * Common adapter for OrderNewAdditionallyFargment and OrderNewBaseFragment
 */
public class OrderCommonAdapter<T extends NewOrder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> data;

    public OrderCommonAdapter(List<T> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_new, viewGroup, false);
        return new OrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OrderVH orderVH = (OrderVH) viewHolder;

        orderVH.tvTitle.setText(data.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateAdapter(List<T> items) {
        data = items;
        notifyDataSetChanged();
    }

    class OrderVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvCounter;
        private ImageButton btnMinus, btnAdd;

        public OrderVH(@NonNull View itemView) {
            super(itemView);
            tvCounter = itemView.findViewById(R.id.tv_count);
            tvTitle = itemView.findViewById(R.id.tv_title);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnAdd = itemView.findViewById(R.id.btn_plus);
        }
    }
}
