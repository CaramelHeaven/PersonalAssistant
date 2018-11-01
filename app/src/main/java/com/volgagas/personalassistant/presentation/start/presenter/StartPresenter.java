package com.volgagas.personalassistant.presentation.start.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class StartPresenter extends BasePresenter<StartView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private String dataCodekey = "";

    public StartPresenter() {
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    public void getUserData(String data) {
        getViewState().requestD365Token();
        getViewState().showProgress();
        repository.getCardInfo(data)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<User, SingleSource<User>>) user -> {
                    if (!user.getCategory().equals(Constants.EQUIPMENT)) {
                        return Single.just(user);
                    } else {
                        return Single.error(new IllegalArgumentException("Equipment"));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResponse, this::handlerErrorsFromBadRequests);
    }

    private void successfulResponse(User user) {
        CacheUser.getUser().setBaseFields(user);
        getViewState().hideProgress();
        Timber.d("COMPLETED DATA");
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        getViewState().hideProgress();
        if (throwable instanceof IllegalArgumentException) {
            getViewState().resultMatchedWithEquipment();
        } else {
            getViewState().commonError();
        }
    }

    public void setDataCodekey(String dataCodekey) {
        this.dataCodekey = dataCodekey;
    }

    public String getDataCodekey() {
        return dataCodekey;
    }
}
