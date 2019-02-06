package com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:31, 31/01/2019.
 */
@InjectViewState
public class NomenclatureDFPresenter extends BasePresenter<NomenclatureDFView<Barcode>> {

    private MainRepository repository;
    private String barcodeResult;

    public NomenclatureDFPresenter(String barcodeResult) {
        super();
        repository = MainRemoteRepository.getInstance();
        this.barcodeResult = barcodeResult;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().showProgress();
        Timber.d("LOAD: " + barcodeResult);
    }

    private void successfulResult(Barcode barcode) {
        getViewState().hideProgress();
        getViewState().showBarcodeResult(barcode);
    }

    public String getBarcodeResult() {
        return barcodeResult;
    }
}
