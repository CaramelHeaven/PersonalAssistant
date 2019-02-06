package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Barcode;

import java.util.List;

/**
 * Created by CaramelHeaven on 10:52, 31/01/2019.
 */
public class BarcodeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Barcode> barcodeList;

    public BarcodeAdapter(List<Barcode> barcodeList) {
        this.barcodeList = barcodeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nomenclature_barcode,
                viewGroup, false);

        return new BarcodeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BarcodeVH barcodeVH = (BarcodeVH) viewHolder;

        barcodeVH.tvTitle.setText(barcodeList.get(i).getBarcodeName());
    }

    @Override
    public int getItemCount() {
        return barcodeList.size();
    }

    public void addValue(Barcode barcode, int position) {
        barcodeList.add(barcode);

        notifyItemChanged(position);
    }

    public List<Barcode> getBarcodeList() {
        return barcodeList;
    }

    class BarcodeVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCount;

        public BarcodeVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);
        }
    }
}
