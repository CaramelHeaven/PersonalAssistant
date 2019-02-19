package com.volgagas.personalassistant.models.mapper.common;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.models.network.ApkResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 12:34, 18/02/2019.
 */
public class ApkResponseToApk extends Mapper<ApkResponse, List<Apk>> {
    @Override
    public List<Apk> map(ApkResponse value) {
        List<Apk> apkes = new ArrayList<>();
        fillData(apkes, value);

        return apkes;
    }

    @Override
    protected void fillData(List<Apk> apks, ApkResponse apkResponse) {
        for (ApkResponse.ApkNetwork apkNetwork : apkResponse.getValue()) {
            Apk apk = new Apk(apkNetwork.getName(), apkNetwork.getServerRelativeUrl());
            apks.add(apk);
        }
    }
}
