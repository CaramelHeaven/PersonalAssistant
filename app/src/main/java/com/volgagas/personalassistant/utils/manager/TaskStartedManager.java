package com.volgagas.personalassistant.utils.manager;

import android.content.Context;

import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.utils.services.SendTaskStartedWorker;

import java.util.List;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

/**
 * Created by CaramelHeaven on 14:20, 16/01/2019.
 * task manager for control SendTaskStartedWorker service class
 */
public class TaskStartedManager {
    private static volatile TaskStartedManager INSTANCE;

    private Context context;
    private OneTimeWorkRequest oneTimeWorkRequest;

    public static TaskStartedManager getInstance() {
        if (INSTANCE == null) {
            synchronized (TaskStartedManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskStartedManager();
                }
            }
        }

        return INSTANCE;
    }

    public OneTimeWorkRequest getWork() {
        return oneTimeWorkRequest;
    }

    public void startBackgroundService(List<SubTaskViewer> list) {
        String[] arrayIdActivities = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arrayIdActivities[i] = list.get(i).getActivityId();
        }

        Data data = new Data.Builder()
                .putStringArray("ID_ACTIVITIES", arrayIdActivities)
                .build();

        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SendTaskStartedWorker.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance()
                .enqueue(oneTimeWorkRequest);
    }
}
