package com.volgagas.personalassistant.presentation.worker_nomenclature_scan.presenter;

import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import retrofit2.Response;

/**
 * Created by CaramelHeaven on 17:43, 15/01/2019.
 */
public class NomenclatureScanPresenter extends BasePresenter<NomenclatureScanView> {

    public NomenclatureScanPresenter() {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void handlerAuthenticationRepeat() {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {

    }
}
