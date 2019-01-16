package com.volgagas.personalassistant.presentation.worker_result.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;
import timber.log.Timber;

@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    private List<SubTask> chosenSubTasks;
    private List<SubTask> allSubTasks;
    private List<SubTask> nonSelectedSubTasks;

    public ResultPresenter(List<SubTask> subTasks) {
        chosenSubTasks = new ArrayList<>();
        nonSelectedSubTasks = new ArrayList<>();
        this.allSubTasks = subTasks;

        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
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
        findNonSelectedSubTasks();
        Timber.d("send SUB TASKS: " + chosenSubTasks);

        Timber.d("non selected tasks: " + nonSelectedSubTasks);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    public List<SubTask> getChosenSubTasks() {
        return chosenSubTasks;
    }

    public void addChosenSubTask(SubTask subTask) {
        chosenSubTasks.add(subTask);
    }

    public void removeChosenSubTask(SubTask subTask) {
        chosenSubTasks.remove(subTask);
    }

    private void findNonSelectedSubTasks() {
        for (SubTask task : allSubTasks) {
            if (!chosenSubTasks.contains(task)) {
                nonSelectedSubTasks.add(task);
            }
        }
    }

    public List<SubTask> getAllSubTasks() {
        return allSubTasks;
    }
}
