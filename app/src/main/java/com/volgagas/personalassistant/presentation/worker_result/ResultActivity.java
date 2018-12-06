package com.volgagas.personalassistant.presentation.worker_result;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.SubTask;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultPresenter;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultView;
import com.volgagas.personalassistant.utils.callbacks.OnResultItemClick;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ResultActivity extends BaseActivity implements ResultView {

    private ResultAdapter adapter;

    private RecyclerView recyclerView;

    @InjectPresenter
    ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.recyclerView);

        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(new SubTask("Raz"));
        subTasks.add(new SubTask("Сломалась труба. Просьба добыть навыки для его усовершенствования"));
        subTasks.add(new SubTask("asld;ads"));
        subTasks.add(new SubTask("lxczxczk;xkckxzzl;cxkc"));
        subTasks.add(new SubTask("djshajkdhsjkdh"));

        setPermissionToEnableNfc(false);

        provideRecyclerAndAdapter(subTasks);
    }

    @Override
    protected void sendDataToServer(String data) {
        //nothing
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideRecyclerAndAdapter(List<SubTask> subTasks) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ResultAdapter(subTasks);
        recyclerView.setAdapter(adapter);

        adapter.setOnResultItemClick(new OnResultItemClick() {
            @Override
            public void onClick(int position, View view, boolean status) {
                if (status) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.colorBackgroundStatusNeutral));
                }
                Timber.d("click");

            }

            @Override
            public void makePhotoClick(int position) {
                Timber.d("click");
            }
        });
    }
}
