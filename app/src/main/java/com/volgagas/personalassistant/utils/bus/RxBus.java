package com.volgagas.personalassistant.utils.bus;

import com.volgagas.personalassistant.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:08, 22/01/2019.
 * Rx bus for update tokens
 */
public class RxBus {
    private static volatile RxBus instance;

    private PublishSubject<String> updateTokenSubject = PublishSubject.create();
    private PublishSubject<String> scanData = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }

        return instance;
    }

    public void passActionForUpdateToken(String action) {
        Timber.d("inside method");
        if (action.equals("UPDATE_TOKEN")) {
            updateTokenSubject.onNext(action);
        } else if (action.equals(Constants.DYNAMICS_TST) ||
                action.equals(Constants.DYNAMICS_PROD)) {
            updateTokenSubject.onNext(action);
        }
    }

    public void passScanData(String data) {
        scanData.onNext(data);
    }

    public Observable<String> getUpdates() {
        return updateTokenSubject;
    }

    public PublishSubject<String> getScanData() {
        return scanData;
    }
}
