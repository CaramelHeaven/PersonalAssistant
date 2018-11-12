package com.volgagas.personalassistant.presentation.projects.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import timber.log.Timber;

public class QueryToUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UniformRequest> data;

    public QueryToUserAdapter(List<UniformRequest> arrayList) {
        this.data = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uniform_request, viewGroup, false);
        return new UniformVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        UniformVH uniformVH = (UniformVH) viewHolder;
        uniformVH.tvTitle.setText(data.get(position).getTitle());
        uniformVH.tvDescription.setText(data.get(position).getDescription());

        Timber.d("DATA: " + data.get(position).getPriority());
        if (data.get(position).getPriority().contains(Constants.PRIORITY_HIGH)) {
            uniformVH.cvPriority.setVisibility(View.VISIBLE);
        } else {
            uniformVH.cvPriority.setVisibility(View.GONE);
        }
    }

    public void updateAdapter(List<UniformRequest> values) {
        data.clear();
        data.addAll(values);
        notifyDataSetChanged();
    }

    public UniformRequest getItemByPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UniformVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        private CardView cvPriority;

        public UniformVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvPriority = itemView.findViewById(R.id.cv_priority);
        }
    }
}
