package com.volgagas.personalassistant.presentation.contracts.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

@InjectViewState
public class ContractPresenter extends BasePresenter<ContractView<Contract>> {

    private MainRepository repository;

    public ContractPresenter() {
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadContracts();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {

    }

    private void loadContracts() {
        getViewState().showContracts(new ArrayList<>());
    }
}
