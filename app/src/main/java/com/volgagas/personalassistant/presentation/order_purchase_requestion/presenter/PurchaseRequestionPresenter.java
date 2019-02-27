package com.volgagas.personalassistant.presentation.order_purchase_requestion.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
        Timber.d("INITIAL");
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
        disposable.add(repository.getPurchasesFromUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showItems(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
