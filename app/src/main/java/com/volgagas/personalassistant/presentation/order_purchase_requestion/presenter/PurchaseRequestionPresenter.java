package com.volgagas.personalassistant.presentation.order_purchase_requestion.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.order.CommonOrder;
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
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:17, 27/02/2019.
 */
@InjectViewState
public class PurchaseRequestionPresenter extends BasePresenter<PurchaseRequestionView> {

    private MainRepository repository;

    public PurchaseRequestionPresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();

        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.PURCHASE_REQUISITION_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.PURCHASE_REQUISITION_MORE);
        } else {
            sendCrashlytics(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().hideProgress();
        getViewState().showItems(CachePot.getInstance().getUserOrders());
    }
}
