package com.volgagas.personalassistant.utils.services;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.RxWorker;
import androidx.work.WorkerParameters;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 10:17, 21/02/2019.
 */
public class UpdateNomenclaturesWorker extends RxWorker {

    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public UpdateNomenclaturesWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @Override
    public Single<Result> createWork() {
        return null;
    }
}
