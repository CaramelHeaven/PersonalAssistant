package com.volgagas.personalassistant.utils.channels.pass_data;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by CaramelHeaven on 17:51, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class PassData {
    private static PassData INSTANCE;
    private static PublishSubject<String> subject;

    public static PassData getInstance() {
        if (INSTANCE == null) {
            synchronized (PassData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PassData();
                    subject = PublishSubject.create();
                }
            }
        }
        return INSTANCE;
    }

    public static PublishSubject<String> getSubject() {
        return subject;
    }

    public static void sendData(String data) {
        subject.onNext(data);
    }
}
