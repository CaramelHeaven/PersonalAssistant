package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class NomenclatureBarcodePresenter extends MvpPresenter<NomenclatureBarcodeView> {

    private CompositeDisposable disposable;
    private MainRepository repository;
    private List<Barcode> barcodeList;

    public NomenclatureBarcodePresenter() {
        disposable = new CompositeDisposable();
        repository = MainRemoteRepository.getInstance();

        barcodeList = new ArrayList<>();
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

    public void loadBarcodeData(String barcodeData) {
        if (!checkIfBarcodeExist(barcodeData)) {
            getViewState().showProgressDialog();

            disposable.add(repository.getBarcodeInfoFromServer(barcodeData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                Timber.d("checking unit: " + result.toString());
                                getViewState().hideProgressDialog();
                                getViewState().successfulGetBarcodeFromServer(result);
                            },
                            this::handlerWithError));
        }
    }

    private void handlerWithError(Throwable throwable) {
        Crashlytics.logException(throwable);
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    private boolean checkIfBarcodeExist(String barcodeNum) {
        for (Barcode barcode : barcodeList) {
            if (barcode.getBarcode().equals(barcodeNum)) {
                getViewState().successfulGetBarcodeFromServer(barcode);

                return true;
            }
        }

        return false;
    }
}
