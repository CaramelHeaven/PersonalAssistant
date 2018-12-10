package com.volgagas.personalassistant.utils.services;

import android.os.Handler;

import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.UpdateToken;

import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

public class UpdateTokensService {

    private static UpdateTokensService INSTANCE;
    private static TimerTask timerTask;
    private static Timer timer;
    // 1 minute * 9 minutes
    private static final int DELAY = 60000 * 9;

    public static UpdateTokensService getInstance() {
        if (INSTANCE == null) {
            synchronized (UpdateTokensService.class) {
                if (INSTANCE == null) {
                    Timber.d("prepare to start");
                    prepareTimer();
                    INSTANCE = new UpdateTokensService();
                    timer = new Timer();
                }
            }
        }
        return INSTANCE;
    }

    public static void startTimer() {
        new Handler().postDelayed(() -> {
            Timber.d("run");
            timer.purge();
            timer.scheduleAtFixedRate(timerTask, 0, DELAY);
        }, 5000);
    }

    private static void prepareTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                refreshTokens();
            }
        };
    }

    public static void onApplicationClose() {
        timerTask.cancel();
        timer.purge();
    }

    private static void refreshTokens() {
        GlobalBus.getEventBus().post(new UpdateToken("updated tok"));
    }
}
