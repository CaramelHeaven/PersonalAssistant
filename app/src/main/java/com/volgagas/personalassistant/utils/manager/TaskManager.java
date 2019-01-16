package com.volgagas.personalassistant.utils.manager;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;

import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.utils.services.SendTaskStartedWorker;

import java.util.Arrays;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:20, 16/01/2019.
 * task manager for control SendTaskStartedWorker service class
 */
public class TaskManager implements LifecycleOwner {
    private static volatile TaskManager INSTANCE;

    private Context context;
    private OneTimeWorkRequest oneTimeWorkRequest;

    public static TaskManager getInstance() {
        if (INSTANCE == null) {
            synchronized (TaskManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskManager();
                }
            }
        }

        return INSTANCE;
    }

    public void initContext(Context context) {
        this.context = context;


    }

    public OneTimeWorkRequest getWork() {
        return oneTimeWorkRequest;
    }

    public void startBackgroundService(Task task) {
        String[] arrayIdActivities = new String[task.getSubTasks().size()];

        for (int i = 0; i < task.getSubTasks().size(); i++) {
            arrayIdActivities[i] = task.getSubTasks().get(i).getIdActivity();
        }

        Timber.d("check array: " + Arrays.toString(arrayIdActivities));

        Data data = new Data.Builder()
                .putStringArray("ID_ACTIVITIES", arrayIdActivities)
                .build();

        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SendTaskStartedWorker.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance()
                .enqueue(oneTimeWorkRequest);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
