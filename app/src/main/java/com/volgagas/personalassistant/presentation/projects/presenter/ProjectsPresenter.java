package com.volgagas.personalassistant.presentation.projects.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.common.QueriesCommon;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:45, 16.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private MainRepository repository;

    public ProjectsPresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.PROJECTS_SCREEN))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));

        loadData();
    }

    private void successfulResult(QueriesCommon queriesCommon) {
        CachePot.getInstance().setQueryFromUserList(queriesCommon.getQueryFromUserList());
        CachePot.getInstance().setQueryToUserList(queriesCommon.getQueryToUserList());

        RxBus.getInstance().passDataToCommonChannel(Constants.PROJECTS_QUERY_TO_USER_PRESENTER);
        RxBus.getInstance().passDataToCommonChannel(Constants.PROJECTS_UNIFORM_PRESENTER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.PROJECTS_SCREEN);
        } else {
            Crashlytics.logException(throwable);
            getViewState().showError(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        disposable.add(Single.zip(repository.getUniformRequestsToUser(),
                repository.getUniformRequestsFromUser(),
                (queryToUsers, uniformRequests) -> {
                    QueriesCommon queriesCommon = new QueriesCommon();
                    queriesCommon.setQueryFromUserList(uniformRequests);
                    queriesCommon.setQueryToUserList(queryToUsers);

                    return queriesCommon;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }
}
