package com.volgagas.personalassistant.presentation.worker_result.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    private List<SubTask> subTaskList;

    public ResultPresenter() {
        subTaskList = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void sendData() {
        Timber.d("YEEEE SEND");
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void addSubTask(SubTask subTask) {
        subTaskList.add(subTask);
    }

    public void removeSubTask(SubTask subTask) {
        subTaskList.remove(subTask);
    }
}
