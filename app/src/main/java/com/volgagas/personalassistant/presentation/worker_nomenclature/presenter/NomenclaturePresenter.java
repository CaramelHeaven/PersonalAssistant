package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by CaramelHeaven on 17:28, 15/01/2019.
 */
@InjectViewState
public class NomenclaturePresenter extends BasePresenter<NomenclatureView> {

    public NomenclaturePresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        // loadData();
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

    public List<Nomenclature> loadData() {
        List<Nomenclature> nomenclatures = new ArrayList<>();

        Nomenclature nomenclature = new Nomenclature();
        Nomenclature nomenclature1 = new Nomenclature();
        Nomenclature nomenclature2 = new Nomenclature();

        nomenclature.setName("Alalala");
        nomenclature.setCount("0");
        nomenclature1.setName("Alalala 2");
        nomenclature1.setCount("0");
        nomenclature2.setName("Alalala 3");
        nomenclature2.setCount("0");

        nomenclatures.add(nomenclature);
        nomenclatures.add(nomenclature1);
        nomenclatures.add(nomenclature2);

        return nomenclatures;
    }
}
