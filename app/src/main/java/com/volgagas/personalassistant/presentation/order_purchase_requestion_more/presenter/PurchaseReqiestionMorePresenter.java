package com.volgagas.personalassistant.presentation.order_purchase_requestion_more.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 18:47, 27/02/2019.
 */
@InjectViewState
public class PurchaseReqiestionMorePresenter extends BasePresenter<PurchaseReqiestionMoreView> {

    private MainRepository repository;
    private String orderId;

    public PurchaseReqiestionMorePresenter(String orderId) {
        super();
        this.orderId = orderId;
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {

        } else {
            sendCrashlytics(throwable);
            getViewState().catastrophicError(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getPurchaseRequisitionLines(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showItem(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
