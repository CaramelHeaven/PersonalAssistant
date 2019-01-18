package com.volgagas.personalassistant.presentation.worker_gpa.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

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
public class GpaPresenter extends BasePresenter<GpaView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private Task task;

    public GpaPresenter(Task task) {
        this.task = TaskContentManager.getInstance().getTask();

        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
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

    public void sendData(String userNumbers) {
        getViewState().showProgress();
        disposable.add(repository.getCardInfo(userNumbers)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<User, SingleSource<Boolean>>) gpa -> {
                    Timber.d("GPA: " + gpa);
                    Timber.d("name: " + gpa.getName());
                    Timber.d("get GPA: " + task.getGpa());
                    if (gpa.getCategory().equals("Оборудование") && (gpa.getName().equals(task.getGpa()))) {
                        return Single.just(true);
                    } else {
                        return Single.just(false);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    private void successfulResult(Boolean aBoolean) {
        if (aBoolean) {
            getViewState().completed();
        } else {
            getViewState().showErrorEquipment();
        }
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        Timber.d("handler: " + result.toString());
        if (result.size() > 0) {
            Timber.d("to string: " + result.size());
            if (result.get(0).code() == Constants.HTTP_400) {
                Timber.d("check result: " + result.toString());
                getViewState().showError("Произошла ошибка на стороне сервера");
            } else if (result.get(0).code() == Constants.HTTP_204) {
                getViewState().completed();
            }
        } else {
            getViewState().completed();
        }
    }

    private void successfulResult(List<Response<Void>> result) {
        getViewState().hideProgress();
        if (result != null) {
            handlerErrorInSuccessfulResult(result);
        } else {
            getViewState().completed();
        }
    }

    public Task getTask() {
        return task;
    }
}
