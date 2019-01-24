package com.volgagas.personalassistant.presentation.worker_today_new.presenter;

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

/**
 * Created by CaramelHeaven on 12:41, 23/01/2019.
 */
@InjectViewState
public class WorkerTodayNewPresenter extends BasePresenter<WorkerTodayNewView<Task>> {



    private CompositeDisposable disposable;
    private MainRepository repository;

    public WorkerTodayNewPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getTasksToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::unsuccessfulResult));
    }

    private void unsuccessfulResult(Throwable throwable) {

    }

    private void successfulResult(List<Task> tasks) {
        getViewState().hideProgress();
        for (Task task : tasks){
            Timber.d("check Tasks: " + task.toString());
        }
        getViewState().showItems(tasks);
    }


    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }
}
