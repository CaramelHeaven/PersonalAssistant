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
            boolean isAllCompleted = true;
            int indexTask = 0;

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).code() != Constants.HTTP_204) {
                    isAllCompleted = false;
                    break;
                }
            }

            if (isAllCompleted) {
                getViewState().completedKiosk();
            } else {
                getViewState().errorFromCreatedTask("Возникла ошибка при создании задачи с нумерацией " + indexTask + 1);
            }
        } else {
            getViewState().completedKiosk();
        }
    }

    @SuppressLint("CheckResult")
    private void listenerAddedTasks() {
        CommonChannel.getObservableUpdatedTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    senderTasks.clear();
                    senderTasks.addAll(result);
                    Timber.d("size senders: " + senderTasks);
                    Timber.d("checking size: " + senderTasks.size());
                });
    }

    /**
     * Create our templates on server
     */
    @SuppressLint("CheckResult")
    public void sendData() {
        Timber.d("lala; " + senderTasks.toString());
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
            Timber.d("COMPLETED COMPLETEDCOMPLETEDCOMPLETED COMPLETED");
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
