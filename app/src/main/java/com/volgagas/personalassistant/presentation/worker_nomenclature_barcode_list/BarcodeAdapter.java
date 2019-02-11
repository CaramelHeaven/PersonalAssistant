package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by CaramelHeaven on 10:52, 31/01/2019.
 */
public class BarcodeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Barcode> barcodeList;
    private myOnItemClickListener myOnItemClickListener;

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

        barcodeVH.tvTitle.setText(barcodeList.get(i).getBarcodeName() + String.valueOf(barcodeList.get(i).getCount()));
    }

    @Override
    public int getItemCount() {
        return barcodeList.size();
    }

    public void addValue(Barcode barcode, int position) {
        if (barcodeList.contains(barcode)) {
            System.out.println("get count: " + barcodeList.get(position).getCount());
            //Timber.d(
            barcodeList.get(position).setCount(barcodeList.get(position).getCount() + barcode.getCount());
        } else {
            barcodeList.add(barcode);
        }

        notifyItemChanged(position);
    }

    public void removeValueByPos(int position) {
        barcodeList.remove(position);

        notifyItemRemoved(position);
    }

    public List<Object> getBarcodeList() {
        return new ArrayList<>(barcodeList);
    }

    class BarcodeVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCount;
        ImageButton ibRemove;

        public BarcodeVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);
            ibRemove = itemView.findViewById(R.id.ib_remove);

            ibRemove.setOnClickListener(v -> myOnItemClickListener.onItemClick(getAdapterPosition()));
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
