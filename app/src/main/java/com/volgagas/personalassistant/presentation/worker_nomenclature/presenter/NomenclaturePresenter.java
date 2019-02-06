package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:28, 15/01/2019.
 */
@InjectViewState
public class NomenclaturePresenter extends BasePresenter<NomenclatureView> {

    private MainRepository repository;
    private Task task;

    public NomenclaturePresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
        task = TaskContentManager.getInstance().getTask();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(RxBus.getInstance().getScanData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addDataFromNfc, this::handlerErrorsFromBadRequests));

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .subscribeOn(Schedulers.io())
                .filter(result -> result.equals(Constants.WORKER_NOMENCLATURE_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("IM HERE FUCK YOU");
                    loadData();
                }));

        PersonalAssistant.provideDynamics365Auth("addasdas", "");

        loadData();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            Timber.d("401 pass");
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_NOMENCLATURE_PRESENTER);
        } else {
            Timber.d("THROWABLE: " + throwable.getMessage());
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    private void addDataFromNfc(String data) {
        disposable.add(repository.findNomenclatureByCardInfo(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getName().equals("")) {
                        getViewState().errorNomenclature();
                    } else {
                        getViewState().addNomenclatureToBaseList(result);
                    }
                }));
    }

    @SuppressLint("CheckResult")
    protected void loadData() {
        Timber.d("LOAD DATA");
        getViewState().showProgress();
        disposable.add(repository.getNomenclaturesBySO(task.getIdTask())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("COMPLETED");
                    Nomenclature nomenclature = new Nomenclature("kek", 0, "шт");
                    result.add(nomenclature);
                    getViewState().hideProgress();
                    getViewState().showBaseList(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
