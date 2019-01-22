package com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by CaramelHeaven on 12:03, 22/01/2019.
 */
@InjectViewState
public class ChooseActivityPresenter extends BasePresenter<ChooseActivityView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public ChooseActivityPresenter() {
        this.repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposable.add(RxBus.getInstance().getStatementNfc().subscribe(result -> {
            getViewState().enableNFC();
        }));
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
}
