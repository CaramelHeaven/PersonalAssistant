package com.volgagas.personalassistant.presentation.queries.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class QueryPresenter extends BasePresenter<QueryView<UniformRequest>> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public QueryPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("query presenter injected");
        getViewState().showProgress();
        disposable.add(repository.getUniformRequestsFromUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResultFromUser, this::handlerErrorsFromBadRequests));
    }

    private void successfulResultToUser(List<UniformRequest> uniformRequests) {
        getViewState().hideProgress();
    }

    private void successfulResultFromUser(List<UniformRequest> uniformRequests) {
        getViewState().hideProgress();
        Timber.d("SHOW ITEMS: " + uniformRequests.size());
        getViewState().showItems(uniformRequests);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    public void openMessages() {
        Timber.d("send data");
    }
}
