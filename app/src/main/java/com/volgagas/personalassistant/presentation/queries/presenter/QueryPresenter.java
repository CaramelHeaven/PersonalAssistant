package com.volgagas.personalassistant.presentation.queries.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class QueryPresenter extends BasePresenter<QueryView<QueryToUser, UniformRequest>> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public QueryPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("query presenter injected");
        getViewState().showProgress();

        disposable.add(Single.zip(repository.getUniformRequestsFromUser(), repository.getUniformRequestsToUser(),
                (BiFunction<List<UniformRequest>, List<QueryToUser>, CommonGrapData>) (uniformRequests, queryToUsers) -> {
                    CommonGrapData<UniformRequest, QueryToUser> commonGrapData = new CommonGrapData<>();

                    commonGrapData.setUniformRequests(uniformRequests);
                    commonGrapData.setQueryToUsers(queryToUsers);

                    return commonGrapData;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));
    }

    private void successfulResult(CommonGrapData<UniformRequest, QueryToUser> data) {
        getViewState().hideProgress();
        getViewState().showValues(data.getQueryToUsers(), data.getUniformRequests());
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

    /* Small class for grab values from two single and in the end - split it
     * */
    private class CommonGrapData<T1 extends UniformRequest, T2 extends QueryToUser> {
        private List<T1> uniformRequests;
        private List<T2> queryToUsers;

        public List<T1> getUniformRequests() {
            return uniformRequests;
        }

        public void setUniformRequests(List<T1> uniformRequests) {
            this.uniformRequests = uniformRequests;
        }

        public List<T2> getQueryToUsers() {
            return queryToUsers;
        }

        public void setQueryToUsers(List<T2> queryToUsers) {
            this.queryToUsers = queryToUsers;
        }
    }
}
