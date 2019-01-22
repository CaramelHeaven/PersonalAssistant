package com.volgagas.personalassistant.utils;

import android.os.Handler;
import android.os.HandlerThread;

import com.volgagas.personalassistant.utils.bus.RxBus;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:28, 22/01/2019.
 */
public class UpdateTokenHandler extends HandlerThread {

    private Handler handler;
    private final int time = 1000 * 60 * 10; // 1 second * 60 seconds * 10 minutes

    public UpdateTokenHandler(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(getLooper());

        handler.post(runnable);
    }

    public void postTask(Runnable task) {
        if (handler != null) {
            handler.post(task);
        }
    }

    public void removePeriodicWork() {
        handler.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Timber.d("called periodic work");
            RxBus.getInstance().passActionForUpdateToken("UPDATE_TOKEN");

            handler.postDelayed(runnable, time);
        }
    };
}
