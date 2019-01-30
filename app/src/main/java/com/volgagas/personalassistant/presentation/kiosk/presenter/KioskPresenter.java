package com.volgagas.personalassistant.presentation.kiosk.presenter;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.channels.CommonChannel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:53, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class KioskPresenter extends BasePresenter<KioskView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    private List<TaskTemplate> senderTasks;

    public KioskPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
        listenerAddedTasks();
        senderTasks = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        if (result.size() > 0) {
            String effect = "";

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).code() == Constants.HTTP_204) {
                    effect = "OK";
                    break;
                } else if (result.get(i).code() == Constants.HTTP_400) {
                    effect = "400";
                    break;
                }
            }

            if (effect.equals("OK")) {
                getViewState().completedKiosk();
            } else if (effect.equals("400")) {
                getViewState().errorFromCreatedTask("Ошибка 400 при создании задач");
            } else {
                getViewState().errorFromCreatedTask("Ошибка при создании. ");
            }
        } else {
            getViewState().completedKiosk();
        }
    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {

    }

    @SuppressLint("CheckResult")
    private void listenerAddedTasks() {
        CommonChannel.getObservableUpdatedTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    senderTasks.clear();
                    senderTasks.addAll(result);
                });
    }

    /**
     * Map our chosen tasks and send it to server
     */
    @SuppressLint("CheckResult")
    public void sendData() {
        getViewState().sendTemplatesProgress();
        disposable.add(Single.just(senderTasks)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<TaskTemplate>, Iterable<TaskTemplate>>) taskTemplates ->
                        taskTemplates)
                .flatMap((Function<TaskTemplate, ObservableSource<Response<Void>>>) taskTemplate -> {
                    String query = "(dataAreaId='gns',ServiceOrderId='" + taskTemplate.getServiceOrderId() + "')";
                    JsonObject object = new JsonObject();
                    object.add("pn", new JsonPrimitive(CacheUser.getUser().getPersonalDynamics365Number()));

                    return repository.sendTemplateTasks(query, object);
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    private void successfulResult(List<Response<Void>> responses) {
        if (responses != null) {
            handlerErrorInSuccessfulResult(responses);
        } else {
            getViewState().completedKiosk();
        }
    }

    public void addTask(TaskTemplate taskTemplate) {
        senderTasks.add(taskTemplate);
    }

    public void removeTask(TaskTemplate taskTemplate) {
        senderTasks.remove(taskTemplate);
    }

    public List<TaskTemplate> getSenderTasks() {
        return senderTasks;
    }
}
