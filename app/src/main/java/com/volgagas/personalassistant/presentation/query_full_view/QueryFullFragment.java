package com.volgagas.personalassistant.presentation.query_full_view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.query_full_view.presenter.QueryFullPresenter;
import com.volgagas.personalassistant.presentation.query_full_view.presenter.QueryFullView;

/**
 * Created by CaramelHeaven on 11:06, 13.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryFullFragment extends MvpAppCompatDialogFragment implements QueryFullView {

    @InjectPresenter
    QueryFullPresenter presenter;

    public static QueryFullFragment newInstance() {

        Bundle args = new Bundle();

        QueryFullFragment fragment = new QueryFullFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query_full, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
