package com.volgagas.personalassistant.presentation.fill_request;

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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.choose_category.CategoryDialogFragment;
import com.volgagas.personalassistant.presentation.fill_request.presenter.FillRequestPresenter;
import com.volgagas.personalassistant.presentation.fill_request.presenter.FillRequestView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

/**
 * Created by CaramelHeaven on 15:49, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class FillRequestFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, FillRequestView {

    private TextInputEditText etEventName, etDescription;
    private TextView tvDate, tvCategory;
    private RelativeLayout rlDateContainer, rlCategoryContainer;
    private Button btnNextStep;
    private Switch switchImportant;

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
        switchImportant = view.findViewById(R.id.switch_important);
        tvCategory = view.findViewById(R.id.tv_category);
        rlCategoryContainer = view.findViewById(R.id.rl_category_container);

        provideClickListeners();
        btnNextStep.setOnClickListener(v -> {
            if (etDescription.getText().toString().length() == 0
                    || etEventName.getText().toString().length() == 0
                    || tvDate.getCurrentTextColor() != getResources().getColor(R.color.colorTextBlack)
                    || tvCategory.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "Не отмечены все поля", Toast.LENGTH_SHORT).show();
            } else {
                RequestData data = new RequestData();

                data.setDescription(etDescription.getText().toString());
                data.setTitle(etEventName.getText().toString());
                data.setEndDate(tvDate.getText().toString());
                data.setImportant(switchImportant.isChecked());
                data.setCategory(presenter.getQueryTemplate().getId());

                presenter.handlerClickButton(data);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getEventBus().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventClickFromDialog(QueryTemplate queryTemplate) {
        presenter.setQueryTemplate(queryTemplate);

        tvCategory.setTextColor(getActivity().getResources().getColor(R.color.colorTextBlack));
        tvCategory.setText(presenter.getQueryTemplate().getTitle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void provideClickListeners() {
        rlDateContainer.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), 0, FillRequestFragment.this, year, month, day);
            datePickerDialog.show();
        });

        rlCategoryContainer.setOnClickListener(v -> {
            CategoryDialogFragment dialogFragment = CategoryDialogFragment.newInstance();
            dialogFragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + month + "-" + dayOfMonth;

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
