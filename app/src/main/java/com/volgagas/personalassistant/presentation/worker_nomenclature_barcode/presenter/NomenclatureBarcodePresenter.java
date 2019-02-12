package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class NomenclatureBarcodePresenter extends MvpPresenter<NomenclatureBarcodeView> {

    private CompositeDisposable disposable;

    public NomenclatureBarcodePresenter() {
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        //Listener from animation when view is collapsed
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.VIEW_IS_COLLAPSED) ||
                        result.equals(Constants.NOMENCLATURE_DF_CLOSE))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().resumeBarcode()));

        //Listener where we send barcode list to NomenclatureFragment UI
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.PASS_DATA_BARCODE))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().getDataAndComlpeted()));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
