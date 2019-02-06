package com.volgagas.personalassistant.utils.threads;

import android.os.Handler;
import android.os.HandlerThread;

import com.volgagas.personalassistant.utils.bus.RxBus;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:28, 22/01/2019.
 * Simple foreground thread for update token each 10 minutes
 */
public class UpdateTokenHandler extends HandlerThread {

    private Handler handler;
    private final int time = 1000 * 60 * 10; // 1000 milliseconds (1 second) * 60 seconds * 10

    public UpdateTokenHandler(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(getLooper());

        handler.postDelayed(runnable, time);
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

           // RxBus.getInstance().passActionForUpdateToken("UPDATE_TOKEN_HANDLER");
            handler.postDelayed(runnable, time);
        }
    };
}
