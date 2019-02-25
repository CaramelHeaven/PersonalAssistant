package com.volgagas.personalassistant.presentation.worker_today_new.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.ContainerTaskAndHistory;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:41, 23/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class WorkerTodayNewPresenter extends BasePresenter<WorkerTodayNewView<Task>> {
    private CompositeDisposable disposable;
    private MainRepository repository;

    public WorkerTodayNewPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_TODAY_NEW_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> commonLoadTasksTodayAndHistory()));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        commonLoadTasksTodayAndHistory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * In the first attempt we load both data from tasks today and histories, if we catch error 401
     * we refreshing token and load it again, we can handler strange bug. Don't touch it. If user want
     * to update this tasks today - it's simple calls loadData method in here
     */
    private void commonLoadTasksTodayAndHistory() {
        getViewState().showProgress();
        disposable.add(Single.zip(repository.getTasksToday(), repository.getHistoryTasks(),
                ContainerTaskAndHistory::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showItems(result.getTaskList());

                    CachePot.getInstance().putTaskHistories(result.getTaskHistories());

                    RxBus.getInstance().passDataToCommonChannel(Constants.WORKER_HISTORY_COMES_DATA);
                }, this::handlerErrorsFromBadRequests));
    }

    public void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getTasksToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    private void successfulResult(List<Task> tasks) {
        getViewState().hideProgress();
        getViewState().showItems(tasks);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_TODAY_NEW_PRESENTER);
        } else {
            sendCrashlytics(throwable);
            getViewState().catastrophicError(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        if (result.size() > 0) {
            for (Response<Void> response : result) {
                Timber.d("sout: " + response.code());
            }
            Timber.d("checking size: " + result.toString());
        } else {

        }
    }

}
