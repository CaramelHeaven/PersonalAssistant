package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:53, 15/01/2019.
 */
public class NomenclatureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Nomenclature> nomenclatureList;

    private OnButtonPlusMinusClickListener onButtonPlusMinusClickListener;

    public NomenclatureAdapter(List<Nomenclature> nomenclatureList) {
        this.nomenclatureList = nomenclatureList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nomenclature, viewGroup, false);

        return new NomenclatureVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NomenclatureVH nomenclatureVH = (NomenclatureVH) viewHolder;

        nomenclatureVH.tvCount.setText(nomenclatureList.get(i).getCount());
        nomenclatureVH.tvTitle.setText(nomenclatureList.get(i).getName());
    }

    public void updateItemCount(int position, int newCount) {
        if (newCount >= 0) {
            nomenclatureList.get(position).setCount(String.valueOf(newCount));
        }

        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return nomenclatureList.size();
    }

    class NomenclatureVH extends RecyclerView.ViewHolder {
        Button btnAdd, btnMinus;
        TextView tvTitle, tvCount;

        public NomenclatureVH(@NonNull View itemView) {
            super(itemView);
            btnAdd = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvTitle = itemView.findViewById(R.id.tv_title);

            btnMinus.setOnClickListener(v -> {
                Timber.d("minus");
                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 0,
                        Integer.parseInt(tvCount.getText().toString()));
            });

            btnAdd.setOnClickListener(v -> {
                Timber.d("add");
                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 1,
                        Integer.parseInt(tvCount.getText().toString()));
            });
        }
    }

    public void setOnButtonPlusMinusClickListener(OnButtonPlusMinusClickListener onButtonPlusMinusClickListener) {
        this.onButtonPlusMinusClickListener = onButtonPlusMinusClickListener;
    }
}
