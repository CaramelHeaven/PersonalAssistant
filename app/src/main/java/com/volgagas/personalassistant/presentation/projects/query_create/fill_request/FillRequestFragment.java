package com.volgagas.personalassistant.presentation.projects.query_create.fill_request;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects.query_create.fill_request.presenter.FillRequestPresenter;
import com.volgagas.personalassistant.presentation.projects.query_create.fill_request.presenter.FillRequestView;

import java.util.Calendar;

/**
 * Created by CaramelHeaven on 15:49, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class FillRequestFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, FillRequestView {

    private TextInputEditText etEventName, etDescription;
    private TextView tvDate;
    private RelativeLayout rlDateContainer;
    private Button btnNextStep;

    @InjectPresenter
    FillRequestPresenter presenter;

    public static FillRequestFragment newInstance() {

        Bundle args = new Bundle();

        FillRequestFragment fragment = new FillRequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fill_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etEventName = view.findViewById(R.id.et_event_name);
        etDescription = view.findViewById(R.id.et_description);
        tvDate = view.findViewById(R.id.tv_date);
        rlDateContainer = view.findViewById(R.id.rl_date_container);
        btnNextStep = view.findViewById(R.id.btn_apply);

        provideCalendarPicker();
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handlerClickButton();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void provideCalendarPicker() {
        rlDateContainer.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), 0, FillRequestFragment.this, year, month, day);
            datePickerDialog.show();
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "" + dayOfMonth + "/" + month + "/" + year;
        tvDate.setTextColor(getResources().getColor(R.color.colorTextBlack));
        tvDate.setText(date);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
