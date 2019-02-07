package com.volgagas.personalassistant.presentation.about_user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.Info;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:24, 07/02/2019.
 */
public class InfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objectList;

    private final int TYPE_INFO = -1;
    private final int TYPE_USUALLY = 0;

    public InfoAdapter(List<Object> objectList) {
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_INFO:
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_info_salary_and_vacation, viewGroup, false);
                return new SalaryVacationVH(view);
            case TYPE_USUALLY:

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_INFO:
                SalaryVacationVH salaryVacationVH = (SalaryVacationVH) viewHolder;
                if (objectList.get(position) instanceof Info) {
                    salaryVacationVH.tvSalary
                            .setText(((Info) objectList.get(position)).getSalary().getSalaryUser());
                    salaryVacationVH.tvVacation
                            .setText(((Info) objectList.get(position)).getVacation().getFreeDays());
                }

                break;
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void updateAdapter(List<Object> objects) {
        if (objectList.size() > 0) {
            objectList.clear();
        }

        objectList.addAll(objects);

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof Info) {
            return TYPE_INFO;
        } else {
            return TYPE_USUALLY;
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
