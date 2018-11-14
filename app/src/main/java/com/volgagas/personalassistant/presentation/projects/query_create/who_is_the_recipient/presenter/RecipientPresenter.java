package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter;

import android.annotation.SuppressLint;
import android.transition.Scene;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class RecipientPresenter extends BasePresenter<RecipientView> {

    private MainRepository repository;
    private PassDataChannel passDataChannel;
    private CompositeDisposable disposable;

    public RecipientPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(repository.getSearchedUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));

        disposable.add(passDataChannel.getInstance().getSubject()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().sendUserData(result)));
    }

    private void successfulResult(List<User> users) {
        getViewState().hideProgress();
        getViewState().showUsers(users);
    }

    @SuppressLint("CheckResult")
    public void sendToServer(RequestData data, List<User> userList) {
        Timber.d("SEND");
        getViewState().showProgress();
        Single.just(userList)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<User>, Iterable<User>>) users -> userList)
                .flatMap(user -> repository.getUserIdByUserName(user.getModifiedNormalName()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("get result: " + result.toString());
                }, t -> {
                    Timber.d("throwable: " + t.getMessage());
                    Timber.d("throwable: " + t.getCause());
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }
}