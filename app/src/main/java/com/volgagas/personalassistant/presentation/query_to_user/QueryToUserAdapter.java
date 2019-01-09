package com.volgagas.personalassistant.presentation.query_to_user;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:24, 24/12/2018.
 */
public class QueryToUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QueryToUser> queryToUsers;

    private myOnItemClickListener myOnItemClickListener;

    public QueryToUserAdapter(List<QueryToUser> queryToUsers) {
        this.queryToUsers = queryToUsers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uniform_request, viewGroup, false);
        return new QueryToUserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        QueryToUserVH queryToUserVH = (QueryToUserVH) viewHolder;

        queryToUserVH.tvDescription.setText(Html.fromHtml(queryToUsers.get(position).getComment()));
        queryToUserVH.tvTitle.setText(queryToUsers.get(position).getTitle());
        queryToUserVH.tvCategory.setText(queryToUsers.get(position).getCategory());

        if (queryToUsers.get(position).getPriority().contains(Constants.PRIORITY_HIGH)) {
            queryToUserVH.ivPriority.setVisibility(View.VISIBLE);
        } else {
            queryToUserVH.ivPriority.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return queryToUsers.size();
    }

    public void updateAdapter(List<QueryToUser> items) {
        queryToUsers = items;
        notifyDataSetChanged();
    }

    public QueryToUser getItemByPosition(int pos) {
        return queryToUsers.get(pos);
    }

    class QueryToUserVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvDescription, tvCategory;
        private CardView cvContainer;
        private ImageView ivPriority;

        public QueryToUserVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPriority = itemView.findViewById(R.id.iv_priority);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvTitle = itemView.findViewById(R.id.tv_title);
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
