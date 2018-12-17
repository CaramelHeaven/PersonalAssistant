package com.volgagas.personalassistant.presentation.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryBase;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

import timber.log.Timber;

public class QueryMiniAdapter<T extends QueryBase> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> data;
    private myOnItemClickListener myOnItemClickListener;

    public QueryMiniAdapter(List<T> arrayList) {
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
        UniformVH baseVH = (UniformVH) viewHolder;

        if (data.get(position) instanceof QueryToUser) {
            baseVH.tvTitle.setText(((QueryToUser) data.get(position)).getTitle());
            baseVH.tvDescription.setText(((QueryToUser) data.get(position)).getComment());

        } else if (data.get(position) instanceof UniformRequest) {
            baseVH.tvTitle.setText(((UniformRequest) data.get(position)).getTitle());
            baseVH.tvDescription.setText(((UniformRequest) data.get(position)).getDescription());

            if (((UniformRequest) data.get(position)).getPriority().contains(Constants.PRIORITY_HIGH)) {
                baseVH.cvPriority.setVisibility(View.VISIBLE);
            } else {
                baseVH.cvPriority.setVisibility(View.GONE);
            }
        }

    }

    public void updateAdapter(List<T> values) {
        data.clear();
        data.addAll(values);

        notifyDataSetChanged();
    }

    public T getItemByPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UniformVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvDescription;
        private CardView cvPriority, cvContainer;

        public UniformVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTitle = itemView.findViewById(R.id.tv_title);
            cvPriority = itemView.findViewById(R.id.cv_priority);
            cvContainer = itemView.findViewById(R.id.cv_container);
            cvContainer.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            myOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setMyOnItemClickListener(com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}
