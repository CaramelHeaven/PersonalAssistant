package com.volgagas.personalassistant.presentation.worker_today.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

@InjectViewState
public class WorkerTodayPresenter extends BasePresenter<WorkerTodayView<Task>> {

    private MainRepository repository;

    public WorkerTodayPresenter() {
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void handlerAuthenticationRepeat() {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    public void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getTasksToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::unsuccessfulResult));
    }

    private void successfulResult(List<Task> tasks) {
        getViewState().hideProgress();
        getViewState().showItems(tasks);
    }

    private void unsuccessfulResult(Throwable throwable) {
        Timber.d("unsuccessful: " + throwable.getMessage());
    }
}
