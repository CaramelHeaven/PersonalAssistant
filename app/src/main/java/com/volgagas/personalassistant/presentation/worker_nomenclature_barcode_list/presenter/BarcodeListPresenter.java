package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:40, 06/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */

@InjectViewState
public class BarcodeListPresenter extends MvpPresenter<BarcodeListView> {

    private CompositeDisposable disposable;

    public BarcodeListPresenter() {
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(RxBus.getInstance().getCommonChannel()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(result -> result.equals(Constants.EXPANDED) || result.equals(Constants.COLLAPSED))
                .flatMap((Function<String, ObservableSource<Boolean>>) s -> {
                    if (s.equals(Constants.EXPANDED)) {
                        return Observable.just(true);
                    } else {
                        return Observable.just(false);
                    }
                })
                .subscribe(result -> getViewState().stateOfLayout(result)));

        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.REQUEST_DATA_FROM_BARCODE_LIST))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().grabData()));

        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.UPDATE_DATA_BARCODE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().updateItem(CachePot.getInstance().getBarcode())));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
