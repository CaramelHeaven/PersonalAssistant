package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import retrofit2.Response;

/**
 * Created by CaramelHeaven on 17:28, 15/01/2019.
 */
public class NomenclaturePresenter extends BasePresenter<NomenclatureView> {

    public NomenclaturePresenter() {
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
}
