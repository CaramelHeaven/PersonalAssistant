package com.volgagas.personalassistant.presentation.about_user_certificates;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:23, 27/02/2019.
 */
public class CertificatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PersonCertificates> certificates;

    public CertificatesAdapter(List<PersonCertificates> certificates) {
        this.certificates = certificates;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_info_user_certificates,
                viewGroup, false);

        return new CertificatesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return certificates.size();
    }

    public void updateAdapter(List<PersonCertificates> values) {
        certificates = values;
        notifyDataSetChanged();
    }

    class CertificatesVH extends RecyclerView.ViewHolder {

        public CertificatesVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
