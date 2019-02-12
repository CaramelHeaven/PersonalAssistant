package com.volgagas.personalassistant.presentation.query_more_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryBase;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.messenger.MessengerActivity;
import com.volgagas.personalassistant.utils.Constants;

import java.util.regex.Pattern;

/**
 * Created by CaramelHeaven on 13:56, 29/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryMoreDetailsDialogFragment extends MvpAppCompatDialogFragment {

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvTime;
    private TextView tvCategory;
    private TextView tvPriority;
    private Button btnOpenMessages;
    private Toolbar toolbar;
    private ImageView ivPriority;

    private DisplayMetrics displayMetrics;

    public static QueryMoreDetailsDialogFragment newInstance(QueryBase model) {

        Bundle args = new Bundle();
        args.putParcelable("MODEL", model);

        QueryMoreDetailsDialogFragment fragment = new QueryMoreDetailsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_query_more_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTitle = view.findViewById(R.id.tv_title);
        tvDescription = view.findViewById(R.id.tv_description);
        tvTime = view.findViewById(R.id.tv_time);
        tvPriority = view.findViewById(R.id.tv_priority);
        btnOpenMessages = view.findViewById(R.id.btn_open_messages);
        tvCategory = view.findViewById(R.id.tv_category);
        toolbar = view.findViewById(R.id.toolbar);
        ivPriority = view.findViewById(R.id.iv_priority);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> dismiss());

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        QueryBase model = getArguments().getParcelable("MODEL");

        if (model instanceof UniformRequest) {
            tvTitle.setText(((UniformRequest) model).getTitle());
            tvDescription.setText(Html.fromHtml(((UniformRequest) model).getDescription()));
            tvTime.setText(((UniformRequest) model).getEndedTime());
        } else if (model instanceof QueryToUser) {
            tvTitle.setText(((QueryToUser) model).getTitle());
            tvDescription.setText(Html.fromHtml(((QueryToUser) model).getComment()));
            tvTime.setText(((QueryToUser) model).getDate());
            tvCategory.setText(((QueryToUser) model).getCategory());

            if (((QueryToUser) model).getPriority().contains(Constants.PRIORITY_HIGH)) {
                tvPriority.setText("Высокий приоритет");
            } else {
                ivPriority.setImageDrawable(getActivity().getDrawable(R.drawable.ic_priority_normal));
                tvPriority.setTextColor(getActivity().getResources().getColor(R.color.colorPriorityNormal));
                tvPriority.setText("Обычный приоритет");
            }
        }

        btnOpenMessages.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MessengerActivity.class)));
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 360);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
