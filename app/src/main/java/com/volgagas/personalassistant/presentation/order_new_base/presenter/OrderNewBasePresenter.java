package com.volgagas.personalassistant.presentation.order_new_base.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 13:19, 25/12/2018.
 */
@InjectViewState
public class OrderNewBasePresenter extends BasePresenter<OrderNewBaseView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public OrderNewBasePresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {
        disposable.add(repository.getOrderNewBase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().showItems(result)));
    }
}
