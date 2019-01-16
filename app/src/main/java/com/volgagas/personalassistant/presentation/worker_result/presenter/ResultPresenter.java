package com.volgagas.personalassistant.presentation.worker_result.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    @SuppressLint("CheckResult")
    public void sendData() {
        findNonSelectedSubTasks();
        getViewState().showSendStatus();

        JsonObject completedJson = new JsonObject();
        JsonObject canceledJson = new JsonObject();

        completedJson.add("ActivityState", new JsonPrimitive("Completed"));
        completedJson.add("PhaseId", new JsonPrimitive("Завершено"));
        canceledJson.add("ActivityState", new JsonPrimitive("Completed"));
        canceledJson.add("PhaseId", new JsonPrimitive("Отменено"));

        Single.just(chosenSubTasks)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                        repository.sendCompletedSubTasks(completedJson, subTask.getIdActivity()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(suc -> {
                    getViewState().completed();
                    Timber.d("sucL: " + suc);
                }, throwable -> {
                    Timber.d("THROWAB: " + throwable.getMessage());
                });
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
