package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
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
            //non
        } else {
            NomenclatureVH nomenclatureVH = (NomenclatureVH) viewHolder;

            nomenclatureVH.tvTitle.setText(nomenclatureList.get(i).getName());

            nomenclatureVH.etCount.setText(String.valueOf(nomenclatureList.get(i).getCount()));
            nomenclatureVH.etCount.setSelection(nomenclatureVH.etCount.getText().length());

            nomenclatureVH.tvUnit.setText(nomenclatureList.get(i).getUnit());
        }
    }

    public Nomenclature getItemByPosition(int pos) {
        return nomenclatureList.get(pos);
    }

    @Override
    public int getItemCount() {
        return nomenclatureList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == nomenclatureList.size()) {
            return NOMENCLATURE_SCAN;
        }

        return position;
    }

    public void updateAdapter(List<Nomenclature> values) {
        nomenclatureList.clear();
        nomenclatureList.addAll(values);

        notifyDataSetChanged();
    }

    public void addItem(Nomenclature data) {
        if (nomenclatureList.contains(data)) {
            int index = nomenclatureList.indexOf(data);

            nomenclatureList.get(index).setCount(nomenclatureList.get(index).getCount() + data.getCount());
        } else {
            nomenclatureList.add(data);
        }

        notifyItemChanged(nomenclatureList.size() - 1);
    }

    public void addItems(List<Nomenclature> values) {
        nomenclatureList.addAll(values);

        notifyDataSetChanged();
    }

    public List<Nomenclature> getNomenclatureList() {
        return nomenclatureList;
    }

    /**
     * @return boolean value. If each nomenclature equals 0 count - return true, else - false
     */
    public boolean isNomenclaturesCountEqualsNull() {
        Timber.d("check not: " + nomenclatureList.toString());
        for (Nomenclature item : nomenclatureList) {
            if (item.getCount() != 0) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        nomenclatureList.clear();
    }

    class NomenclatureScanVH extends RecyclerView.ViewHolder {
        public NomenclatureScanVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    class NomenclatureVH extends RecyclerView.ViewHolder {
        Button btnAdd, btnMinus;
        TextView tvTitle, tvUnit;
        TextInputEditText etCount;
        private RelativeLayout rlContainer;

        public NomenclatureVH(@NonNull View itemView) {
            super(itemView);
            btnAdd = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            etCount = itemView.findViewById(R.id.et_count);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvUnit = itemView.findViewById(R.id.tv_unit);

            provideEditTextListener();
            provideButtonsClick();
        }

        String afterText = "";

        private void provideEditTextListener() {
            etCount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    afterText = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //Handler output text which user can be enter to edit text. Removes 0 or other
                    // situation. Don't touch it.
                    if (s.toString().length() > 1) {
                        if (s.toString().equals("0") || s.toString().equals("00") || s.toString().equals("000")) {
                            etCount.setText("0");
                            etCount.setSelection(etCount.getText().length());
                        } else {
                            //remove 0 if we can get text like this 09
                            int tempCount = 0;
                            for (int i = 0; i < s.toString().length(); i++) {
                                if (s.charAt(i) == '0') {
                                    tempCount++;
                                } else {
                                    break;
                                }
                            }
                            if (tempCount > 0) {
                                etCount.setText(afterText.substring(tempCount, afterText.length()));
                                etCount.setSelection(etCount.getText().length());
                            }
                        }
                    }

                    if (!s.toString().equals("")) {
                        onButtonPlusMinusClickListener.onHandleEditText(getAdapterPosition(),
                                Integer.parseInt(etCount.getText().toString()));
                    }
                }
            });
        }

        private void provideButtonsClick() {
            btnMinus.setOnClickListener(v -> {
                int count = 0;

                if (!etCount.getText().toString().equals("")) {
                    if (Integer.parseInt(etCount.getText().toString()) > 0) {
                        count = Integer.parseInt(etCount.getText().toString());
                        count--;
                        etCount.setText(String.valueOf(count));
                    } else {
                        etCount.setText(String.valueOf(0));
                    }
                } else {
                    etCount.setText(String.valueOf(0));
                }

                etCount.setSelection(etCount.getText().length());
                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 0, count);
            });

            btnAdd.setOnClickListener(v -> {
                int count;

                if (etCount.getText().toString().equals("")) {
                    count = 1;
                } else {
                    count = Integer.parseInt(etCount.getText().toString());
                    count++;
                }

                etCount.setText(String.valueOf(count));
                etCount.setSelection(etCount.getText().length());
                onButtonPlusMinusClickListener.onHandleCount(getAdapterPosition(), 1, count);
            });
        }
    }

    public void setOnButtonPlusMinusClickListener(OnButtonPlusMinusClickListener onButtonPlusMinusClickListener) {
        this.onButtonPlusMinusClickListener = onButtonPlusMinusClickListener;
    }
}
