package com.volgagas.personalassistant.presentation.main.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.notifications.FileUploadNotification;
import com.volgagas.personalassistant.utils.services.SaveApkWorker;
import com.volgagas.personalassistant.utils.threads.UpdateTokenHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private UpdateTokenHandler updateTokenHandler;
    private CompositeDisposable disposable;
    private MainRepository repository;
    private boolean permissionToDownloadFile = false;

    public MainPresenter() {
        disposable = new CompositeDisposable();
        repository = MainRemoteRepository.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        updateTokenHandler = new UpdateTokenHandler("UpdateTokenHandler");
        updateTokenHandler.start();

        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.ACTION_FOR_DOWNLOAD_APK))
                .subscribe(result -> downloadOrNotNewestVersion()));
    }

    @Override
    public void onDestroy() {
        updateTokenHandler.removePeriodicWork();
        updateTokenHandler.quit();

        disposable.clear();

        super.onDestroy();
    }

    //method for download newest apk from server
    private void downloadOrNotNewestVersion() {
        String apkName = CachePot.getInstance().getApk().getName();
        getViewState().createProgressNotification(); //need for pass context inside FileUploadNotification

        FileUploadNotification.shared().createNotification(100, "Скачивание файла");

        disposable.add(repository.downloadNewestApk(apkName)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                        FileUploadNotification.shared().updateNotification("15"))
                .doOnSuccess(body ->
                        FileUploadNotification.shared().updateNotification("30"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("COMPLETED");
                    CachePot.getInstance().clearApk();
                    CachePot.getInstance().setBodyApk(result);

                    // Service for save apk
                    OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SaveApkWorker.class)
                            .build();

                    WorkManager.getInstance()
                            .enqueue(oneTimeWorkRequest);
                }, throwable -> {
                    Timber.d("throwable: " + throwable.getMessage());
                    Timber.d("throwable: " + throwable.getCause());
                }));
    }

    /**
     * Match all available versions
     *
     * @param apkList - current apk list after filtered baseApkList
     * @return - null or newest Apk
     */
    private Apk findNewestVersion(List<Apk> apkList) {
        Map<Apk, Integer> apkMap = new HashMap<>();
        List<Integer> versions = new ArrayList<>();
        Apk newestApk = new Apk();

        for (Apk apk : apkList) {
            int intVersion = Integer.parseInt(apk.getName()
                    .replaceAll("[^0-9]", ""));

            apkMap.put(apk, intVersion);
            versions.add(intVersion);
        }

        int maxVersionFromNetwork = Collections.max(versions);
        int versionFromApp = Integer.parseInt(Constants.APP_CURRENT_VERSION.replaceAll("[^0-9]", ""));

        int currentMax = Math.max(maxVersionFromNetwork, versionFromApp);

        // if current max higher - get apk class by version and return it
        if (currentMax != versionFromApp) {
            for (Map.Entry<Apk, Integer> entry : apkMap.entrySet()) {
                if (entry.getValue() == currentMax) {
                    newestApk.setName(entry.getKey().getName());
                    newestApk.setUrlReference(entry.getKey().getUrlReference());

                    return newestApk;
                }
            }
        }

        return null;
    }

    /**
     * Filtering our list of apks from folder
     *
     * @param baseApk - base list from folder
     * @return apkList - filtered apks which contains "pa" name and don't contains test apkes
     */
    private List<Apk> filterApkListForCurrentAppName(List<Apk> baseApk) {
        List<Apk> apkList = new ArrayList<>();
        for (Apk apk : baseApk) {
            if (apk.getName().toLowerCase().contains("pa") && (!apk.getName().toLowerCase().contains("test"))) {
                apkList.add(apk);
            }
        }

        return apkList;
    }

    /**
     * If permissions accepted we can check list of our current apkes
     */
    public void acceptToCheckListOfApkes() {
        disposable.add(repository.getCurrentListApkes()
                .subscribeOn(Schedulers.io())
                .map(this::filterApkListForCurrentAppName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("all result: " + result);
                    Apk apk = findNewestVersion(result);
                    if (apk != null) {
                        Timber.d("NEWEST APK: " + apk.toString());
                        CachePot.getInstance().saveApk(apk); // save apk for the future use
                        RxBus.getInstance().passDataToCommonChannel(Constants.UPDATE_APK);
                    }
                }, throwable -> getViewState().showError(throwable)));
    }
}
