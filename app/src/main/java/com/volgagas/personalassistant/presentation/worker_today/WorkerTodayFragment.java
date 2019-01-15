package com.volgagas.personalassistant.presentation.worker_today;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_today.presenter.WorkerTodayPresenter;
import com.volgagas.personalassistant.presentation.worker_today.presenter.WorkerTodayView;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class WorkerTodayFragment extends BaseFragment implements WorkerTodayView<Task> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;
    private TextView tvCountTasks, tvDateNumber, tvDateDay, tvDate, tvEmptyTasks;
    private ImageView ivEmptyTasks, ivPictureTasksCount;

    private TodayAdapter adapter;

    @InjectPresenter
    WorkerTodayPresenter presenter;

    public static WorkerTodayFragment newInstance() {

        Bundle args = new Bundle();

        WorkerTodayFragment fragment = new WorkerTodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress_bar);
        swipeRefresh = view.findViewById(R.id.spl_update_content);
        tvCountTasks = view.findViewById(R.id.tv_count_tasks);
        tvDateNumber = view.findViewById(R.id.tv_today);
        tvDateDay = view.findViewById(R.id.tv_day_of_week);
        tvDate = view.findViewById(R.id.tv_date);
        tvEmptyTasks = view.findViewById(R.id.tv_empty_tasks);
        ivEmptyTasks = view.findViewById(R.id.iv_empty_tasks);
        ivPictureTasksCount = view.findViewById(R.id.iv_list);

        provideRecyclerAndAdapter();
        provideCurrentDate();
        provideRefreshingItems();
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        progressBar = null;
        swipeRefresh = null;
        tvCountTasks = null;
        tvDateNumber = null;
        tvDateDay = null;
        tvEmptyTasks = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {
        tvEmptyTasks.setVisibility(View.GONE);
        ivPictureTasksCount.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new TodayAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            Timber.d("click");
            Timber.d("click");
        });
    }

    @Override
    public void showItems(List<Task> models) {
        if (models.size() != 0) {
            adapter.updateItems(models);

            tvCountTasks.setText(models.size() + " задач");
            ivPictureTasksCount.setVisibility(View.VISIBLE);
        } else {
            tvEmptyTasks.setVisibility(View.VISIBLE);
            ivEmptyTasks.setVisibility(View.VISIBLE);

            ivPictureTasksCount.setVisibility(View.GONE);
            tvCountTasks.setVisibility(View.GONE);
        }
    }

    private void provideRefreshingItems() {

    }

    private void provideCurrentDate() {
        String month = "", year = "", dayOfWeek = "", day = "";
        String time = PersonalAssistant.getCurrentDataFormat();
        DateFormat formatMonth = new SimpleDateFormat("MMMM", new Locale("RU"));
        DateFormat formatYear = new SimpleDateFormat("yyyy", new Locale("RU"));
        DateFormat formatDay = new SimpleDateFormat("dd", new Locale("RU"));
        DateFormat formatDayOfWeek = new SimpleDateFormat("EEEE", new Locale("RU"));
        try {
            Date dateMain = PersonalAssistant.getPatternFromServer().parse(time);
            StringBuilder helper = new StringBuilder();
            month = helper
                    .append(formatMonth.format(dateMain))
                    .insert(0, formatMonth.format(dateMain).substring(0, 1).toUpperCase())
                    .deleteCharAt(1)
                    .toString();
            helper.setLength(0);

            dayOfWeek = helper
                    .append(formatDayOfWeek.format(dateMain))
                    .insert(0, formatDayOfWeek.format(dateMain).substring(0, 1).toUpperCase())
                    .deleteCharAt(1)
                    .toString();
            helper.setLength(0);

            year = helper
                    .append(formatYear.format(dateMain))
                    .toString();
            helper.setLength(0);

            day = helper
                    .append(formatDay.format(dateMain))
                    .insert(0, formatDay.format(dateMain).substring(0, 1).toUpperCase())
                    .deleteCharAt(1)
                    .toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvDateNumber.setText(day);
        tvDateDay.setText(dayOfWeek);
        tvDate.setText(month + " " + year);
    }
}
