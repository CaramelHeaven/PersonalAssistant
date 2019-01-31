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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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
        disposable.clear();
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

        disposable.add(Single.just(chosenSubTasks)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                        repository.sendCompletedSubTasks(completedJson, subTask.getIdActivity()))
                .toList()
                .observeOn(Schedulers.computation())
                .flatMap((Function<List<Response<Void>>, SingleSource<List<SubTask>>>) objects ->
                        Single.just(findPictures(chosenSubTasks)))
                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                        repository.sendImageToDynamics(mapImage(subTask))
                                .subscribeOn(Schedulers.computation()))
                .toList()
                .observeOn(Schedulers.io())
                .flatMap((Function<List<Response<Void>>, SingleSource<List<SubTask>>>) objects ->
                        Single.just(nonSelectedSubTasks))
                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                .flatMap((Function<SubTask, ObservableSource<?>>) subTask ->
                        repository.sendCanceledSubTasks(canceledJson, subTask.getIdActivity()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(suc -> getViewState().completed(), throwable -> {
                    Timber.d("THROWAB: " + throwable.getMessage());
                    Timber.d("kek: " + throwable.getCause());
                }));
    }

    /**
     * Method for map image to base64 before we send it
     */
    private JsonObject mapImage(SubTask subTask) {
        File imageFile = new File(subTask.getFilePath());

        JsonObject object = new JsonObject();
        if (imageFile.exists() && imageFile.length() > 0) {
            Bitmap bitmap = BitmapFactory.decodeFile(subTask.getFilePath());
            Matrix matrix = new Matrix();

            matrix.postRotate(90);

            Bitmap bitmapRotated = Bitmap.createBitmap(bitmap,
                    0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapRotated.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);

            object.add("ActivityNumber", new JsonPrimitive(subTask.getIdActivity()));
            object.add("dataAreaId", new JsonPrimitive("gns"));
            object.add("ImageNumber", new JsonPrimitive("1"));
            object.add("Image",
                    new JsonPrimitive(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)));
        }

        return object;
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {

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

    private List<SubTask> findPictures(List<SubTask> subTasks) {
        List<SubTask> listWithImages = new ArrayList<>();
        for (SubTask subTask : subTasks) {
            if (subTask.getFilePath() != null) {
                if (subTask.getFilePath().length() > 0) {
                    listWithImages.add(subTask);
                }
            }
        }

        return listWithImages;
    }

    public List<SubTask> getAllSubTasks() {
        return allSubTasks;
    }
}
