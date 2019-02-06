package com.volgagas.personalassistant.presentation.query_from_user.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:23, 24/12/2018.
 */
@InjectViewState
public class UniformsPresenter extends BasePresenter<QueryFromUserView<UniformRequest>> {

    private CompositeDisposable disposable;
    private MainRepository repository;

    public UniformsPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();
        loadData();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    private void result(List<UniformRequest> requests) {
        getViewState().hideProgress();
        getViewState().showItems(requests);
    }

    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        disposable.add(repository.getUniformRequestsFromUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::result, this::handlerErrorsFromBadRequests));
    }
}
