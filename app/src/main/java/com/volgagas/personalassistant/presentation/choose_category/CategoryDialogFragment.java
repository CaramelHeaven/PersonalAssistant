package com.volgagas.personalassistant.presentation.choose_category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.presentation.choose_category.presenter.CategoryPresenter;
import com.volgagas.personalassistant.presentation.choose_category.presenter.CategoryView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 12:23, 14.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class CategoryDialogFragment extends MvpAppCompatDialogFragment implements CategoryView<QueryTemplate> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private DisplayMetrics displayMetrics;
    private CategoryAdapter adapter;

    @InjectPresenter
    CategoryPresenter presenter;

    public static CategoryDialogFragment newInstance() {

        Bundle args = new Bundle();

        CategoryDialogFragment fragment = new CategoryDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress_bar);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 280);
        window.setGravity(Gravity.CENTER);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new CategoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            GlobalBus.getEventBus().post(adapter.getItemByPosition(position));

            dismiss();
        });
    }

    @Override
    public void showItems(List<QueryTemplate> items) {
        if (items.size() > 0) {
            adapter.updateAdapter(items);
        }
    }
}
