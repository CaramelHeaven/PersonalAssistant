package com.volgagas.personalassistant.presentation.order_purchase_order.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import retrofit2.Response;

/**
 * Created by CaramelHeaven on 16:17, 27/02/2019.
 */
@InjectViewState
public class PurchaseOrderPresenter extends BasePresenter<PurchaseOrderView> {

    public PurchaseOrderPresenter() {
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
    protected void loadData() {

    }
}
