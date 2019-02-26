package com.volgagas.personalassistant.presentation.about_user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.model.info.PersonSalary;
import com.volgagas.personalassistant.models.model.info.PersonSkills;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:24, 07/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class InfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objectList;

    private final int TYPE_INFO = -1;
    private final int TYPE_DATA = -2;
    private final int TYPE_SKILLS = -3;
    private final int TYPE_USUALLY = 0;

    public InfoAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_INFO:
                View info = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_info_salary_and_vacation, viewGroup, false);
                return new SalaryVacationVH(info);
            case TYPE_DATA:
                View data = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_info_data, viewGroup, false);
                return new DataVH(data);
            case TYPE_USUALLY:
                View skills = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_info_skills, viewGroup, false);
                return new SkillsVH(skills);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_INFO:
                SalaryVacationVH salaryVacationVH = (SalaryVacationVH) viewHolder;
                if (objectList.get(position) instanceof PersonSalary) {
                    salaryVacationVH.tvSalary
                            .setText(((PersonSalary) objectList.get(position)).getSalary());
                    salaryVacationVH.tvVacation
                            .setText("30");
                }
                break;
            case TYPE_DATA:
                DataVH dataVH = (DataVH) viewHolder;
                if (objectList.get(position) instanceof PersonData) {
                    dataVH.tvBirthday.setText(((PersonData) objectList.get(position)).getBirthDate());
                    dataVH.tvAddress.setText(((PersonData) objectList.get(position)).getAddressStreet());
                    dataVH.tvPhoneNumbers.setText(((PersonData) objectList.get(position)).getContactPhone());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void updateAdapter(List<Object> objects) {
        this.objectList = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) != null) {
            if (objectList.get(position) instanceof PersonSalary) {
                return TYPE_INFO;
            } else if (objectList.get(position) instanceof PersonData) {
                return TYPE_DATA;
            } else if (objectList.get(position) instanceof PersonSkills) {
                return TYPE_SKILLS;
            } else {
                return TYPE_USUALLY;
            }
        } else {
            Timber.d("object list null, pos: " + position);
            return position;
        }
    }

    class SkillsVH extends RecyclerView.ViewHolder {

        public SkillsVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    class DataVH extends RecyclerView.ViewHolder {
        TextView tvPhoneNumbers, tvBirthday, tvAddress;

        public DataVH(@NonNull View itemView) {
            super(itemView);
            tvPhoneNumbers = itemView.findViewById(R.id.tv_phone_numbers);
            tvBirthday = itemView.findViewById(R.id.tv_birthday);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }

    class SalaryVacationVH extends RecyclerView.ViewHolder {
        private TextView tvSalary, tvVacation;

        public SalaryVacationVH(@NonNull View itemView) {
            super(itemView);
            tvSalary = itemView.findViewById(R.id.tv_salary);
            tvVacation = itemView.findViewById(R.id.tv_vacation);
        }
    }
}
