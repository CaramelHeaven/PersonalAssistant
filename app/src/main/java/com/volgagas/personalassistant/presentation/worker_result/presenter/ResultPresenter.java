package com.volgagas.personalassistant.presentation.worker_result.presenter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import timber.log.Timber;

@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    private List<SubTask> chosenSubTasks;
    private List<SubTask> allSubTasks;
    private List<SubTask> nonSelectedSubTasks;

    public ResultPresenter(List<SubTask> subTasks) {
        chosenSubTasks = new ArrayList<>();
        nonSelectedSubTasks = new ArrayList<>();
        this.allSubTasks = subTasks;

        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    public void sendData() {
        findNonSelectedSubTasks();
        getViewState().showSendStatus();

        JsonObject completedJson = new JsonObject();
        JsonObject canceledJson = new JsonObject();

        completedJson.add("ActivityState", new JsonPrimitive("Completed"));
        completedJson.add("PhaseId", new JsonPrimitive("Завершено"));
        canceledJson.add("ActivityState", new JsonPrimitive("Completed"));
        canceledJson.add("PhaseId", new JsonPrimitive("Отменено"));

        Timber.d("files : " + chosenSubTasks.toString());
//        Single.just(chosenSubTasks)
//                .subscribeOn(Schedulers.computation())
//                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
//                .flatMap((Function<SubTask, ObservableSource<Observable<Object>>>) subTask ->
//                        Observable.zip(repository.sendCompletedSubTasks(completedJson, subTask.getIdActivity()),
//                                repository.sendImageToDynamics(mapImage(subTask)).subscribeOn(Schedulers.computation()),
//                                (BiFunction<Response<Void>, Response<Void>, Observable<Object>>) (voidResponse, voidResponse2) ->
//                                        Observable.just(new Object())))
//                .toList()
//                .flatMap((Function<List<Observable<Object>>, Single<List<SubTask>>>) observables ->
//                        Single.just(nonSelectedSubTasks))
//                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
//                .flatMap((Function<SubTask, ObservableSource<?>>) subTask ->
//                        repository.sendCanceledSubTasks(canceledJson, subTask.getIdActivity()))
//                .toList()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(suc -> {
//                    getViewState().completed();
//                    Timber.d("sucL: " + suc);
//                }, throwable -> {
//                    Timber.d("THROWAB: " + throwable.getMessage());
//                });
    }


    private JsonObject mapImage(SubTask subTask) {
        Bitmap bitmap = BitmapFactory.decodeFile(subTask.getFilePath());
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap bitmapRotated = Bitmap.createBitmap(bitmap,
                0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapRotated.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);

        JsonObject object = new JsonObject();

        object.add("ActivityNumber", new JsonPrimitive(subTask.getIdActivity()));
        object.add("dataAreaId", new JsonPrimitive("gns"));
        object.add("ImageNumber", new JsonPrimitive("0"));
        object.add("Image",
                new JsonPrimitive(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)));

        return object;
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    public List<SubTask> getChosenSubTasks() {
        return chosenSubTasks;
    }

    public void addChosenSubTask(SubTask subTask) {
        chosenSubTasks.add(subTask);
    }

    public void removeChosenSubTask(SubTask subTask) {
        chosenSubTasks.remove(subTask);
    }

    private void findNonSelectedSubTasks() {
        for (SubTask task : allSubTasks) {
            if (!chosenSubTasks.contains(task)) {
                nonSelectedSubTasks.add(task);
            }
        }
    }

    public List<SubTask> getAllSubTasks() {
        return allSubTasks;
    }
}
