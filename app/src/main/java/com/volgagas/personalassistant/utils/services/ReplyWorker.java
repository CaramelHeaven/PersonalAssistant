package com.volgagas.personalassistant.utils.services;

import android.content.Context;
import android.support.annotation.NonNull;

import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:26, 09/01/2019.
 */
public class ReplyWorker extends Worker {

    private MainRepository repository;

    public ReplyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        repository = MainRemoteRepository.getInstance();

        Timber.d("do something");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Result.success();
    }
}
