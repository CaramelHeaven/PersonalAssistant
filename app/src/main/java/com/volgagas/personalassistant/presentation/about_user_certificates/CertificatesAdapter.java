package com.volgagas.personalassistant.presentation.about_user_certificates;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:23, 27/02/2019.
 */
public class CertificatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PersonCertificates> certificatesList;

    public CertificatesAdapter(List<PersonCertificates> certificates) {
        this.certificatesList = certificates;
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
        CertificatesVH certificatesVH = (CertificatesVH) viewHolder;

        certificatesVH.tvTitle.setText(certificatesList.get(i).getCertificate());
        certificatesVH.tvDescription.setText(certificatesList.get(i).getNotes());
        certificatesVH.tvTime.setText(certificatesList.get(i).getStartTime());
    }

    @Override
    public int getItemCount() {
        return certificatesList.size();
    }

    public void updateAdapter(List<PersonCertificates> values) {
        certificatesList = values;
        notifyDataSetChanged();
    }

    class CertificatesVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvTime;

        public CertificatesVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
