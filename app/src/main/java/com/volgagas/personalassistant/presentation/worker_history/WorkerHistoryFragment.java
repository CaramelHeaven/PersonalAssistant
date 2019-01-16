package com.volgagas.personalassistant.presentation.worker_history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_history.presenter.WorkerHistoryPresenter;
import com.volgagas.personalassistant.presentation.worker_history.presenter.WorkerHistoryView;
import com.volgagas.personalassistant.presentation.worker_task.TaskDialogFragment;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class WorkerHistoryFragment extends BaseFragment implements WorkerHistoryView<TaskHistory> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private HistoryAdapter adapter;

    @InjectPresenter
    WorkerHistoryPresenter presenter;

    public static WorkerHistoryFragment newInstance() {

        Bundle args = new Bundle();

        WorkerHistoryFragment fragment = new WorkerHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        provideRecyclerAndAdapter();
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

        adapter = new HistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            TaskDialogFragment fragment = TaskDialogFragment
                    .newInstance(adapter.getItemByPosition(position), "HISTORY");
            fragment.show(getActivity().getSupportFragmentManager(), null);
        });
    }

    @Override
    public void showItems(List<TaskHistory> models) {
        if (models.size() != 0) {
            adapter.updateItems(models);
        } else {
            Toast.makeText(getActivity(), "История пуста", Toast.LENGTH_SHORT).show();
        }
    }
}
