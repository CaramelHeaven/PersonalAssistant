package com.volgagas.personalassistant.models.mapper.common;

import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.models.network.ApkResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:38, 18/02/2019.
 */
public class CommonMapper {
    private final ApkResponseToApk apkResponseToApk;

    public CommonMapper(ApkResponseToApk apkResponseToApk) {
        this.apkResponseToApk = apkResponseToApk;
    }

    public List<Apk> map(ApkResponse response) {
        return apkResponseToApk.map(response);
    }
}
