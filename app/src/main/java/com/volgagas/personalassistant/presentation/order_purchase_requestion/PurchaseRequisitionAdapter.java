package com.volgagas.personalassistant.presentation.order_purchase_requestion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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

        userOrderVH.tvDescription.setText(orders.get(i).getName());
        userOrderVH.tvTitle.setText("Заказ № " + orders.get(i).getRequisitionNumber());

        Context context = userOrderVH.cvContainer.getContext();
        switch (orders.get(i).getStatus()) {
            case "Closed":
                userOrderVH.cvContainer.setCardBackgroundColor(context.getResources()
                        .getColor(R.color.colorStatusClosed));
                userOrderVH.tvStatus.setText("Завершено");
                break;
            case "Approved":
                userOrderVH.cvContainer.setCardBackgroundColor(context.getResources()
                        .getColor(R.color.colorStatusApproved));
                userOrderVH.tvStatus.setText("Подтверждено");
                break;
            case "InReview":
                userOrderVH.cvContainer.setCardBackgroundColor(context.getResources()
                        .getColor(R.color.colorStatusInReview));
                userOrderVH.tvStatus.setText("Рассматривается");
                break;
            case "Draft":
                userOrderVH.cvContainer.setCardBackgroundColor(context.getResources()
                        .getColor(R.color.colorStatusDraft));
                userOrderVH.tvStatus.setText("Шаблон");
                break;
            default:
                userOrderVH.cvContainer.setCardBackgroundColor(context.getResources()
                        .getColor(R.color.colorStatusReject));
                userOrderVH.tvStatus.setText("Отклонено");
        }
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
        CardView cvContainer;

        public UserOrderVH(@NonNull View itemView) {
            super(itemView);
            cvContainer = itemView.findViewById(R.id.cv_container);
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
