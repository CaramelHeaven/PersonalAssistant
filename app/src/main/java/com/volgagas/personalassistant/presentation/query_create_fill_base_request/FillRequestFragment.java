package com.volgagas.personalassistant.presentation.query_create_fill_base_request;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.volgagas.personalassistant.presentation.query_create_choose_category.CategoryDialogFragment;
import com.volgagas.personalassistant.presentation.query_create_fill_base_request.presenter.FillRequestPresenter;
import com.volgagas.personalassistant.presentation.query_create_fill_base_request.presenter.FillRequestView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:49, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class FillRequestFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, FillRequestView {

    private TextInputEditText etEventName, etDescription;
    private TextView tvDate, tvCategory;
    private TextInputLayout tilEventName, tilDescription;
    private RelativeLayout rlDateContainer, rlCategoryContainer;
    private Button btnNextStep;
    private Switch switchImportant;

    private String currentDate;

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
        tilDescription = view.findViewById(R.id.text_input_description);
        tilEventName = view.findViewById(R.id.text_input_user);

        provideClickListeners();
        btnNextStep.setOnClickListener(v -> {
            if (etDescription.getText().toString().length() == 0
                    || etEventName.getText().toString().length() == 0
                    || tvDate.getCurrentTextColor() != getResources().getColor(R.color.colorTextBlack)
                    || tvCategory.getText().toString().length() == 0) {
                animationShareViews();
                Toast.makeText(getActivity(), "Не отмечены все поля", Toast.LENGTH_SHORT).show();
            } else {
                RequestData data = new RequestData();

                data.setDescription(etDescription.getText().toString());
                data.setTitle(etEventName.getText().toString());
                data.setEndDate(currentDate);
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
        Calendar.getInstance().get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();

        //successful click
        if (calendar.get(Calendar.YEAR) <= year &&
                calendar.get(Calendar.MONTH) <= month &&
                calendar.get(Calendar.DAY_OF_MONTH) <= dayOfMonth) {
            month += 1;
            String dateToView = dayOfMonth + "." + month + "." + year;
            currentDate = year + "-" + month + "-" + dayOfMonth;

            tvDate.setTextColor(getResources().getColor(R.color.colorTextBlack));
            tvDate.setText(dateToView);
        } else {
            Toast.makeText(getActivity(), "Выбрана неправильная дата", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(getActivity(), "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void animationShareViews() {
        final Animation animationShake = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_shake);

        if (etDescription.getText().toString().length() == 0) {
            tilDescription.startAnimation(animationShake);
        }
        if (etEventName.getText().toString().length() == 0) {
            tilEventName.startAnimation(animationShake);
        }
        if (tvDate.getCurrentTextColor() != getResources().getColor(R.color.colorTextBlack)) {
            rlDateContainer.startAnimation(animationShake);
        }
        if (tvCategory.getText().length() == 0) {
            rlCategoryContainer.startAnimation(animationShake);
        }

        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(100);
        }
    }

}
