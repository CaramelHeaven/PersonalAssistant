package com.volgagas.personalassistant.presentation.worker_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class WorkerHistoryPresenter extends BasePresenter<WorkerHistoryView<Task>> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public WorkerHistoryPresenter() {
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

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    private void loadData() {
        disposable.add(repository.getHistory()
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
