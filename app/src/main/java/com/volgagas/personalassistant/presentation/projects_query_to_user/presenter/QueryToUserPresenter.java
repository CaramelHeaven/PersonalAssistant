package com.volgagas.personalassistant.presentation.projects_query_to_user.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:44, 24/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class QueryToUserPresenter extends BasePresenter<QueryToUserView<QueryToUser>> {

    private MainRepository repository;

    public QueryToUserPresenter() {
        repository = MainRemoteRepository.getInstance();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.PROJECTS_QUERY_TO_USER_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();

        disposable.add(repository.getUniformRequestsToUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::interactionResult, this::handlerErrorsFromBadRequests));
    }

    private void interactionResult(List<QueryToUser> result) {
        Timber.d("checkign result : " + result.toString());
        getViewState().hideProgress();
        getViewState().showItems(result);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.PROJECTS_UNIFORM_PRESENTER);
        } else {
            getViewState().catastrophicError(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {

    }
}
