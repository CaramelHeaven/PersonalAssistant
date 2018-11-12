package com.volgagas.personalassistant.utils.channels.pass_data;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by CaramelHeaven on 17:51, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class PassDataChannel {
    private static PassDataChannel INSTANCE;
    private static PublishSubject<RequestData> subject;

    public static PassDataChannel getInstance() {
        if (INSTANCE == null) {
            synchronized (PassDataChannel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PassDataChannel();
                    subject = PublishSubject.create();
                }
            }
        }
        return INSTANCE;
    }

    public static PublishSubject<RequestData> getSubject() {
        return subject;
    }

    public static void sendData(RequestData data) {
        subject.onNext(data);
    }
}
