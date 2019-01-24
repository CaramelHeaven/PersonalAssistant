package com.volgagas.personalassistant.utils.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.SendStartedTasks;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.work.RxWorker;
import androidx.work.WorkerParameters;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:04, 16/01/2019.
 */
public class SendTaskStartedWorker extends RxWorker {
    private MainRepository repository;

    public SendTaskStartedWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Result> createWork() {
        repository = MainRemoteRepository.getInstance();
        String[] arrayIds = getInputData().getStringArray("ID_ACTIVITIES");

        return Single.just(new ArrayList<>(Arrays.asList(arrayIds)))
                .flattenAsObservable((Function<ArrayList<String>, Iterable<String>>) strings -> strings)
                .flatMap((Function<String, ObservableSource<Response<Void>>>) activityId ->
                        repository.sendStartedSubTasks(mappingJson(), activityId))
                .toList()
                .map(responses -> Result.success())
                .doOnError(throwable -> {
                    Timber.d("THROWABLE: " + throwable.getMessage());
                    Result.retry();
                });
    }

    private JsonObject mappingJson() {
        JsonObject object = new JsonObject();
        object.add("ActivityState", new JsonPrimitive("Started"));
        return object;
    }
}
