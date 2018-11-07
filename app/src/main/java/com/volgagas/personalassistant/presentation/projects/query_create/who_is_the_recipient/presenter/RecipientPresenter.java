package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class RecipientPresenter extends BasePresenter<RecipientView> {

    private MainRepository repository;
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
    }

    private void successfulResult(List<User> users) {
        Timber.d("result size: " + users.size());
        getViewState().showUsers(users);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }
}
