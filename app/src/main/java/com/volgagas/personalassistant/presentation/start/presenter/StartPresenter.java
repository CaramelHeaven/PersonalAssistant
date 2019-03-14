package com.volgagas.personalassistant.presentation.start.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class StartPresenter extends BasePresenter<StartView> {

    private MainRepository repository;
    private String userNumbers;

    @SuppressLint("CheckResult")
    public StartPresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //subscriber to get permissions state for start MainActivity
        disposable.add(CommonChannel.getInstance().getPermissionsSubject()
                .subscribeOn(Schedulers.io())
                .filter(ThreePermissions::allValuesIsTrue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().goToMainMenu()));

        //subscriber to get enable NFC reader
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals("ENABLE_NFC"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().enableNFC()));

        //refresh tokens
        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.START_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));
    }

    public void getUserData(String data) {
        this.userNumbers = data;
        loadData();
    }

    private void successfulResponse(UserDynamics userDynamics) {
        ThreePermissions permissions = ThreePermissions.getInstance();
        if (userDynamics.getPersonalNumber() == null || userDynamics.getWorkerRecId() == null ||
                userDynamics.getPersonalNumber().equals("") || userDynamics.getWorkerRecId().equals("")) {
            permissions.setServer(false);
            CommonChannel.sendPermissions(permissions);

            //Show user if we get card data with empty fields
            getViewState().showErrorToEnter();
        } else {
            CacheUser.getUser().setPersonalDynamics365Number(userDynamics.getPersonalNumber());
            CacheUser.getUser().setWorkerRecId(userDynamics.getWorkerRecId());

            permissions.setServer(true);
            CommonChannel.sendPermissions(permissions);
        }
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        getViewState().hideProgress();
        if (throwable instanceof IllegalArgumentException) {
            getViewState().resultMatchedWithEquipment();
        } else if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.START_PRESENTER);
        } else {
            sendCrashlytics(throwable);
            getViewState().commonError(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        if (userNumbers != null && !userNumbers.equals("")) {
            getViewState().showProgress();
            disposable.add(repository.getCardInfo(userNumbers)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        if (result.getCategory() != null && !result.getCategory().equals(Constants.EQUIPMENT)
                                && result.getName() != null && !result.getName().equals("")) {
                            CacheUser.getUser().setBaseFields(result);
                            getViewState().setCrashlytics();

                            disposable.add(repository.getPersonalUserNumber(result.getName())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::successfulResponse,
                                            this::handlerErrorsFromBadRequests));
                        } else {
                            getViewState().showErrorToEnter();
                        }
                    }, this::handlerErrorsFromBadRequests));
        }
    }
}
