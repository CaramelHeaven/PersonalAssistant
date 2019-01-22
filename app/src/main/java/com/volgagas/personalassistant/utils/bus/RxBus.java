package com.volgagas.personalassistant.utils.bus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by CaramelHeaven on 11:08, 22/01/2019.
 * Rx bus for update tokens
 */
public class RxBus {
    private static volatile RxBus instance;

    private PublishSubject<String> updateTokenSubject = PublishSubject.create();
    private PublishSubject<String> enableNfc = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }

        return instance;
    }

    public void passActionForUpdateToken(String action) {
        if (action.equals("UPDATE_TOKEN")) {
            updateTokenSubject.onNext(action);
        }
    }

    public void passActionForEnableNfc(String action) {
        if (action.equals("ENABLE_NFC")) {
            enableNfc.onNext(action);
        }
    }

    public Observable<String> getUpdates() {
        return updateTokenSubject;
    }

    public Observable<String> getStatementNfc() {
        return enableNfc;
    }
}
