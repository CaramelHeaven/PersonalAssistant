package com.volgagas.personalassistant.presentation.projects_contracts;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ContractAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Contract> contractList;

    private myOnItemClickListener myOnItemClickListener;

    public ContractAdapter(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contract, viewGroup, false);
        return new ContractVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return contractList.size();
    }

    public void updateAdapter(List<Contract> values) {
        contractList = values;
        notifyDataSetChanged();
    }

    class ContractVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvMoney, tvState, tvTitle, tvDateEnd;
        private CardView cvContainer;

        public ContractVH(@NonNull View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_moneys);
            tvState = itemView.findViewById(R.id.tv_state);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvContainer = itemView.findViewById(R.id.cardView);
            tvDateEnd = itemView.findViewById(R.id.tv_date_end);

            cvContainer.setOnClickListener(this::onClick);
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
