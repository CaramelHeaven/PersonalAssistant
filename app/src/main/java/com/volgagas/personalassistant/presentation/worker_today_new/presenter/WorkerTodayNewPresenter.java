package com.volgagas.personalassistant.presentation.worker_today_new.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

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

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_TODAY_NEW_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));

        PersonalAssistant.provideDynamics365Auth("dasd", "");
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
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    private void successfulResult(List<Task> tasks) {
        getViewState().hideProgress();
        getViewState().showItems(tasks);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            Timber.d("send refresh");
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_TODAY_NEW_PRESENTER);
        } else {
            Timber.d("THROWABLE: " + throwable.getMessage());
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
