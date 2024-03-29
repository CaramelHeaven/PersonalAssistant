package com.volgagas.personalassistant.presentation.about_user.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.info.InfoCommon;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.model.info.PersonSalary;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Copyright (c) 2019 VolgaGas. All rights reserved.
 */
@InjectViewState
public class InfoPresenter extends BasePresenter<InfoView> {

    private MainRepository repository;

    public InfoPresenter() {
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .filter(result -> result.equals(Constants.ABOUT_USER))
                .subscribe(result -> loadData()));

        loadData();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.ABOUT_USER);
        } else {
            sendCrashlytics(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void loadData() {
        getViewState().showProgress();

        disposable.add(Single.zip(repository.getInfoAboutUserFromDynamics(),
                repository.getPersonSkills(),
                repository.getPersonSalary(),
                (personData, personSkills, personSalary) -> {
                    CommonObjects objects = new CommonObjects();
                    objects.setPersonSalary(personSalary);
                    objects.setPersonData(personData);
                    objects.setPersonSkills(personSkills);

                    return objects;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    List<Object> list = new ArrayList<>();
                    list.add(result.getPersonSalary());
                    list.add(result.getPersonData());

                    getViewState().hideProgress();

                    getViewState().showData(list);
                    getViewState().showPersonSkills(result.getPersonSkills());
                }, this::handlerErrorsFromBadRequests));
    }

    private class CommonObjects {
        private PersonSalary personSalary;
        private PersonData personData;
        private List<PersonSkills> personSkills;

        public PersonSalary getPersonSalary() {
            return personSalary;
        }

        public void setPersonSalary(PersonSalary personSalary) {
            this.personSalary = personSalary;
        }

        public PersonData getPersonData() {
            return personData;
        }

        public void setPersonData(PersonData personData) {
            this.personData = personData;
        }

        public List<PersonSkills> getPersonSkills() {
            return personSkills;
        }

        public void setPersonSkills(List<PersonSkills> personSkills) {
            this.personSkills = personSkills;
        }
    }
}
