package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 15:35, 25/12/2018.
 * Common adapter for OrderNewAdditionallyFargment and OrderNewBaseFragment
 */
public class OrderCommonAdapter<T extends NewOrder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> data;

    private OnButtonPlusMinusClickListener onButtonPlusMinusClickListener;

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
        orderVH.ivImage.setImageDrawable(orderVH.ivImage
                .getContext()
                .getResources()
                .getDrawable(data.get(i).getIntId(orderVH.ivImage.getContext(), data.get(i).getImageReference())));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateAdapter(List<T> items) {
        data = items;
        notifyDataSetChanged();
    }

    public NewOrder getItemByPosition(int pos) {
        return data.get(pos);
    }

    class OrderVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCounter;
        ImageButton btnMinus, btnAdd;
        ImageView ivImage;

        public OrderVH(@NonNull View itemView) {
            super(itemView);
            tvCounter = itemView.findViewById(R.id.tv_count);
            tvTitle = itemView.findViewById(R.id.tv_title);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnAdd = itemView.findViewById(R.id.btn_plus);
            ivImage = itemView.findViewById(R.id.iv_image);

            setClickListeners();
        }

        private void setClickListeners() {
            btnAdd.setOnClickListener(v -> {
                int count = Integer.parseInt(tvCounter.getText().toString());
                count++;
                tvCounter.setText(String.valueOf(count));

                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 1);
            });

            btnMinus.setOnClickListener(v -> {
                if (Integer.parseInt(tvCounter.getText().toString()) > 0) {
                    int count = Integer.parseInt(tvCounter.getText().toString());
                    count--;
                    tvCounter.setText(String.valueOf(count));
                } else {
                    tvCounter.setText(String.valueOf(0));
                }

                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 0);
            });
        }
    }

    public void setOnButtonPlusMinusClickListener(OnButtonPlusMinusClickListener onButtonPlusMinusClickListener) {
        this.onButtonPlusMinusClickListener = onButtonPlusMinusClickListener;
    }
}
