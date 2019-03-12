package com.volgagas.personalassistant.presentation.order_purchase_order.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 16:17, 27/02/2019.
 */
@InjectViewState
public class PurchaseOrderPresenter extends BasePresenter<PurchaseOrderView> {

    private MainRepository repository;

    public PurchaseOrderPresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .filter(result -> result.equals(Constants.PURCHASE_ORDER_PRESENTER))
                .subscribe(result -> loadData()));

        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.PURCHASE_ORDER_PRESENTER);
        } else {
            sendCrashlytics(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        //common loading purchaseBaseOrder and purchaseRequisitionOrders
        getViewState().showProgress();
        disposable.add(Single.zip(repository.getPurchaseOrders(), repository.getPurchaseRequisitions(), CommonPurchases::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    CachePot.getInstance().setUserOrders(result.getUserOrders());
                    RxBus.getInstance().passDataToCommonChannel(Constants.PURCHASE_REQUISITION_PRESENTER);

                    getViewState().hideProgress();
                    getViewState().showItems(result.getServerOrders());
                }, this::handlerErrorsFromBadRequests));
    }

    private class CommonPurchases {
        private List<ServerOrder> serverOrders;
        private List<UserOrder> userOrders;

        public CommonPurchases(List<ServerOrder> serverOrders, List<UserOrder> userOrders) {
            this.serverOrders = serverOrders;
            this.userOrders = userOrders;
        }

        public List<ServerOrder> getServerOrders() {
            return serverOrders;
        }

        public List<UserOrder> getUserOrders() {
            return userOrders;
        }
    }
}
