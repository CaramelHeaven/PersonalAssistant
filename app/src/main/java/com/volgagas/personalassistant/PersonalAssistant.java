package com.volgagas.personalassistant;

import android.app.Application;

import timber.log.Timber;

public class PersonalAssistant extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
