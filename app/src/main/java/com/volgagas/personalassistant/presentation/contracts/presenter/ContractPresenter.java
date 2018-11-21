package com.volgagas.personalassistant.presentation.contracts.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class ContractPresenter extends BasePresenter<ContractView> {

    private MainRepository repository;
    private CompositeDisposable disposable;

    public ContractPresenter() {
        disposable = new CompositeDisposable();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadContracts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    private void loadContracts() {

    }
}
