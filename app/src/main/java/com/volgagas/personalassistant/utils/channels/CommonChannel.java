package com.volgagas.personalassistant.utils.channels;

import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;
import com.volgagas.personalassistant.utils.channels.check_auth.TwoPermissions;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import java.util.List;
import java.util.Set;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by CaramelHeaven on 12:16, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * Another RxBus. Legasy. Used inside StartActivity and some places in kiosk
 */
public class CommonChannel {
    private static CommonChannel INSTANCE;

    //channels
    private static PublishSubject<RequestData> requestDataSubject;
    private static PublishSubject<ThreePermissions> permissionsSubject;
    private static PublishSubject<String> actionsSubject;
    private static PublishSubject<List<TaskTemplate>> observableUpdatedTasks;
    private static PublishSubject<TwoPermissions> twoPermissionsSubject;

    public static CommonChannel getInstance() {
        if (INSTANCE == null) {
            synchronized (CommonChannel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CommonChannel();
                    requestDataSubject = PublishSubject.create();
                    permissionsSubject = PublishSubject.create();
                    actionsSubject = PublishSubject.create();
                    observableUpdatedTasks = PublishSubject.create();
                    twoPermissionsSubject = PublishSubject.create();
                }
            }
        }
        return INSTANCE;
    }

    public static PublishSubject<RequestData> getRequestDataSubject() {
        return requestDataSubject;
    }

    public static PublishSubject<ThreePermissions> getPermissionsSubject() {
        return permissionsSubject;
    }

    public static PublishSubject<String> getActionsSubject() {
        return actionsSubject;
    }

    public static void sendRequestData(RequestData data) {
        requestDataSubject.onNext(data);
    }

    public static void sendPermissions(ThreePermissions data) {
        permissionsSubject.onNext(data);
    }

    public static void sendActions(String data) {
        actionsSubject.onNext(data);
    }

    public static PublishSubject<List<TaskTemplate>> getObservableUpdatedTasks() {
        return observableUpdatedTasks;
    }

    public static void sendListTasks(List<TaskTemplate> data) {
        observableUpdatedTasks.onNext(data);
    }

    public static void sendTwoPermissions(TwoPermissions data) {
        twoPermissionsSubject.onNext(data);
    }

    public static PublishSubject<TwoPermissions> getTwoPermissionsSubject() {
        return twoPermissionsSubject;
    }
}
