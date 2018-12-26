package com.volgagas.personalassistant.presentation.order_new_bottom_sheet;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:29, 26/12/2018.
 */
public class OrderBottomAdapter<T extends NewOrder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> data;

    public OrderBottomAdapter(List<T> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_new_data_sheet, viewGroup, false);
        return new DataVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DataVH dataVH = (DataVH) viewHolder;

        dataVH.tvTitle.setText(data.get(i).getName());
        dataVH.ivImage.setImageDrawable(dataVH.ivImage
                .getContext()
                .getResources()
                .getDrawable(data.get(i).getIntId(dataVH.ivImage.getContext(), data.get(i).getImageReference())));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateAdapter(List<T> orders) {
        data.clear();
        data.addAll(orders);
        notifyDataSetChanged();
        Timber.d("notify called");
    }

    class DataVH extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage;

        public DataVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
