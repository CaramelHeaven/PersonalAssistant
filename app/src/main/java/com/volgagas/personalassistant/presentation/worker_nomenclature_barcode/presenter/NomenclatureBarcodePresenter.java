package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.utils.bus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 12:10, 31/01/2019.
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
        disposable.add(RxBus.getInstance().getBarcodeBus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().updateBarcodeFromNetwork(result)));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
