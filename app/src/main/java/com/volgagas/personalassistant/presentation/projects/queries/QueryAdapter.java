package com.volgagas.personalassistant.presentation.projects.queries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> data;

    public QueryAdapter(List<String> arrayList) {
        this.data = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
