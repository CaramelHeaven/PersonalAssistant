package com.volgagas.personalassistant.presentation.worker_gpa.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class GpaPresenter extends BasePresenter<GpaView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private List<SubTask> selectedTasks;
    private Task task;
    private String userNumbers = "";

    public GpaPresenter() {
        selectedTasks = TaskContentManager.getInstance().getSubTasks();
        task = TaskContentManager.getInstance().getTask();

        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_GPA_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    //&& gpa.getName().equals(task.getGpa()))
    public void sendData(String userNumbers) {
        this.userNumbers = userNumbers;
        getViewState().showProgress();

        disposable.add(repository.getCardInfo(userNumbers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getCategory().equals("Оборудование")) {
                        //equals(task.getGpa())
                        if (result.getName().length() > 0) {
                            disposable.add(Single.just(selectedTasks)
                                    .subscribeOn(Schedulers.io())
                                    .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                                    .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                                            repository.sendStartedSubTasks(mappingJson(), subTask.getIdActivity()))
                                    .toList()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
                        } else {
                            getViewState().hideProgress();
                            getViewState().showError("Приложена другая карта оборудования");
                        }
                    } else {
                        getViewState().hideProgress();
                        getViewState().showError("Приложена карточка сотрудника");
                    }
                }));
    }

    private void successfulResult(List<Response<Void>> responses) {
        getViewState().hideProgress();
        if (responses.size() > 0) {
            handlerErrorInSuccessfulResult(responses);
        } else {
            getViewState().completed();
        }
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_GPA_PRESENTER);
        } else {
            sendCrashlytics(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        if (result.size() > 0) {
            String error = "";
            for (Response<Void> response : result) {
                if (String.valueOf(response.code()).equals(Constants.HTTP_401)) {
                    error = "401";
                    break;
                } else if (response.code() == Constants.HTTP_400) {
                    error = "400";
                    break;
                }
            }

            switch (error) {
                case "401":
                    RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_GPA_PRESENTER);
                    break;
                case "400":
                    getViewState().showError("Ошибка на стороне сервера");
                    break;
                default:
                    getViewState().completed();
                    break;
            }
        } else {
            getViewState().completed();
        }
    }

    @Override
    protected void loadData() {
        sendData(userNumbers);
    }

    private JsonObject mappingJson() {
        JsonObject object = new JsonObject();
        object.add("ActivityState", new JsonPrimitive("Started"));

        return object;
    }
}
