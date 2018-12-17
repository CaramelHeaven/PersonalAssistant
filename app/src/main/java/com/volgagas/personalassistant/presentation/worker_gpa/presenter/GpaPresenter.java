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

@InjectViewState
public class GpaPresenter extends BasePresenter<GpaView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private Task task;
    private List<SubTask> subTaskList;

    public GpaPresenter(Task task) {
        this.task = task;
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
        subTaskList = mappingSubTasks(task);
        disposable.add(repository.getCardInfo(userNumbers)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<User, SingleSource<Boolean>>) gpa -> {
                    if (gpa.getCategory().equals("Оборудование") && (gpa.getName().equals(task.getGpa()))) {
                        return Single.just(true);
                    } else {
                        return Single.just(false);
                    }
                })
                .flatMap((Function<Boolean, Single<List<SubTask>>>) aBoolean -> {
                    if (aBoolean) {
                        return Single.just(subTaskList);
                    }
                    return Single.error(new Exception("NPE"));
                })
                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTaskList)
                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>)
                        subTask -> repository.sendStartedSubTasks(mappingJson(), subTask.getIdActivity()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    private void successfulResult(List<Response<Void>> result) {
        getViewState().hideProgress();
        if (result != null) {
            if (result.size() > 0) {
                if (result.get(0).code() == 400) {
                    getViewState().showError("Произошла ошибка на стороне сервера");
                }
            } else {
                getViewState().completed();
            }
        } else {
            getViewState().completed();
        }
    }

    public Task getTask() {
        return task;
    }

    private JsonObject mappingJson() {
        JsonObject object = new JsonObject();
        object.add("ActivityState", new JsonPrimitive("Started"));
        return object;
    }

    private List<SubTask> mappingSubTasks(Task task) {
        List<SubTask> subTasks = new ArrayList<>();
        for (SubTask subTask : task.getSubTasks()) {
            if (subTask.getWorker().equals(CacheUser.getUser().getName())) {
                subTasks.add(subTask);
            }
        }
        return subTasks;
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }
}
