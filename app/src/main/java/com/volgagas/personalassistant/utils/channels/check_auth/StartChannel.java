package com.volgagas.personalassistant.utils.channels.check_auth;

import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:41, 01.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */

//Class for pass permissions inside StartActivity
public class StartChannel {

    private static StartChannel INSTANCE;
    private static PublishSubject<ThreePermissions> permissionSubject;

    public static StartChannel getInstance() {
        if (INSTANCE == null) {
            synchronized (StartChannel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StartChannel();
                    permissionSubject = PublishSubject.create();
                }
            }
        }
        return INSTANCE;
    }

    public static PublishSubject<ThreePermissions> getPermissionSubject() {
        return permissionSubject;
    }

    public static void sendData(ThreePermissions data) {
        Timber.d("sending data: " + data.toString());
        permissionSubject.onNext(data);
    }
}
