package com.volgagas.personalassistant.presentation.order_purchase_order_more.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 11:54, 28/02/2019.
 */
@InjectViewState
public class PurchaseOrderMorePresenter extends BasePresenter<PurchaseOrderMoreView> {

    private String orderId;
    private MainRepository repository;

    public PurchaseOrderMorePresenter(String orderId) {
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

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getPurchaseOrderLines(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showItems(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
