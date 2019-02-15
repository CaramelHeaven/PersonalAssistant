package com.volgagas.personalassistant.presentation.worker_result.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ResultPresenter extends BasePresenter<ResultView> {

    private MainRepository repository;

    private List<SubTask> chosenSubTasks;
    private List<SubTask> allSubTasks;
    private List<SubTask> nonSelectedSubTasks;

    private boolean stoppingTasks;

    public ResultPresenter() {
        chosenSubTasks = new ArrayList<>();
        nonSelectedSubTasks = new ArrayList<>();
        this.allSubTasks = TaskContentManager.getInstance().getSubTasks();

        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //Listener for bool value from ResultDialogFragment
        disposable.add(RxBus.getInstance().getResultCallback()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().callbackFromResultDialog(result)));

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_RESULT_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> sendToServer()));
    }

    /**
     * We need to update token before to send data to server.
     */
    public void sendData() {
        getViewState().showSendStatus();
        RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_RESULT_PRESENTER);
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

    private void sendToServer() {
        JsonObject completedJson = new JsonObject();
        JsonObject canceledJson = new JsonObject();
        JsonObject stoppedJson = new JsonObject();

        completedJson.add("ActivityState", new JsonPrimitive("Completed"));
        completedJson.add("PhaseId", new JsonPrimitive("Завершено"));
        canceledJson.add("ActivityState", new JsonPrimitive("Completed"));
        canceledJson.add("PhaseId", new JsonPrimitive("Отменено"));
        stoppedJson.add("PhaseId", new JsonPrimitive("ВРаботе"));

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
                .subscribe(result -> {
                    if (stoppingTasks) {
                        disposable.add(Single.just(nonSelectedSubTasks)
                                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                                        repository.sendCanceledSubTasks(stoppedJson, subTask.getIdActivity()))
                                .toList()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::successfulResult, throwable -> {
                                    if (throwable.getMessage().equals("timeout")) {
                                        getViewState().timeout();
                                    }
                                }));
                    } else {
                        disposable.add(Single.just(nonSelectedSubTasks)
                                .flattenAsObservable((Function<List<SubTask>, Iterable<SubTask>>) subTasks -> subTasks)
                                .flatMap((Function<SubTask, ObservableSource<Response<Void>>>) subTask ->
                                        repository.sendCanceledSubTasks(canceledJson, subTask.getIdActivity()))
                                .toList()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::successfulResult, throwable -> {
                                    if (throwable.getMessage().equals("timeout")) {
                                        getViewState().timeout();
                                    }
                                }));
                    }
                }));
    }

    private void successfulResult(List<Response<Void>> responses) {
        if (responses.size() > 0) {
            handlerErrorInSuccessfulResult(responses);
        } else {
            getViewState().completed();
        }
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        Timber.d("checking: " + throwable.getMessage());
        Timber.d("checking: " + throwable.getCause());
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {
        Timber.d("th: " + result.toString());
        boolean ifError = false;
        for (Response<Void> response : result) {
            if (response.code() != Constants.HTTP_204) {
                ifError = true;
            }
        }

        if (!ifError) {
            getViewState().completed();
        }
    }

    @Override
    protected void loadData() {
        sendData();
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

    public void findNonSelectedSubTasks() {
        nonSelectedSubTasks.clear(); // clear for removing bugs

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

    public List<SubTask> getNonSelectedSubTasks() {
        return nonSelectedSubTasks;
    }

    public void setStoppingTasks(boolean stoppingTasks) {
        this.stoppingTasks = stoppingTasks;
    }
}
