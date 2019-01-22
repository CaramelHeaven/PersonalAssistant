package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:53, 15/01/2019.
 */
public class NomenclatureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Nomenclature> nomenclatureList;

    private int NOMENCLATURE_SCAN = -1;
    private OnButtonPlusMinusClickListener onButtonPlusMinusClickListener;

    public NomenclatureAdapter(List<Nomenclature> nomenclatureList) {
        this.nomenclatureList = nomenclatureList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == NOMENCLATURE_SCAN) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_nomenclature_scan, viewGroup, false);

            return new NomenclatureScanVH(view);
        }
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nomenclature, viewGroup, false);

        return new NomenclatureVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == NOMENCLATURE_SCAN) {
            NomenclatureScanVH nomenclatureScanVH = (NomenclatureScanVH) viewHolder;
            //non
        } else {
            NomenclatureVH nomenclatureVH = (NomenclatureVH) viewHolder;

            nomenclatureVH.tvCount.setText(nomenclatureList.get(i).getCount());
            nomenclatureVH.tvTitle.setText(nomenclatureList.get(i).getName());
        }
    }

    public Nomenclature getItemByPosition(int pos) {
        return nomenclatureList.get(pos);
    }

    @Override
    public int getItemCount() {
        return nomenclatureList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == nomenclatureList.size() - 1) {
            return NOMENCLATURE_SCAN;
        }
        return position;
    }

    class NomenclatureScanVH extends RecyclerView.ViewHolder {

        public NomenclatureScanVH(@NonNull View itemView) {
            super(itemView);
        }
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
                int count = 0;

                if (Integer.parseInt(tvCount.getText().toString()) > 0) {
                    count = Integer.parseInt(tvCount.getText().toString());
                    count--;
                    tvCount.setText(String.valueOf(count));
                } else {
                    tvCount.setText(String.valueOf(0));
                }

                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 0, count);
            });

            btnAdd.setOnClickListener(v -> {
                int count = Integer.parseInt(tvCount.getText().toString());
                count++;

                tvCount.setText(String.valueOf(count));
                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 1, count);
            });
        }
    }

    public void setOnButtonPlusMinusClickListener(OnButtonPlusMinusClickListener onButtonPlusMinusClickListener) {
        this.onButtonPlusMinusClickListener = onButtonPlusMinusClickListener;
    }
}
