package com.volgagas.personalassistant.utils.bus;

import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:08, 22/01/2019.
 * RxBus, this is modern version from GlobalBus
 */
public class RxBus {
    private static volatile RxBus instance;

    //TODO refactoring this shit
    private PublishSubject<String> updateTokenSubject = PublishSubject.create();
    //scan data for scan mifare card
    private PublishSubject<String> scanData = PublishSubject.create();
    //subscribe to updated token in each presenter for refreshing loadData
    private PublishSubject<String> subscribeToUpdateToken = PublishSubject.create();
    //scan barcode result
    private PublishSubject<Barcode> barcodeBus = PublishSubject.create();
    //result callback where we ask user: Want you to stop current activities or not, not refactor
    private PublishSubject<Boolean> resultCallback = PublishSubject.create();

    private PublishSubject<String> commonChannel = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }

        return instance;
    }

    public void passActionForUpdateToken(String action) {
        Timber.d("send 1 raz: " + action);
        updateTokenSubject.onNext(action);
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

    public void passUpdatedToken(String result) {
        subscribeToUpdateToken.onNext(result);
    }

    public PublishSubject<String> getSubscribeToUpdateToken() {
        return subscribeToUpdateToken;
    }

    public PublishSubject<Barcode> getBarcodeBus() {
        return barcodeBus;
    }

    public PublishSubject<Boolean> getResultCallback() {
        return resultCallback;
    }

    public void passResultCallback(Boolean bool) {
        resultCallback.onNext(bool);
    }

    public void passDataToCommonChannel(String data) {
        commonChannel.onNext(data);
    }

    public PublishSubject<String> getCommonChannel() {
        return commonChannel;
    }
}
