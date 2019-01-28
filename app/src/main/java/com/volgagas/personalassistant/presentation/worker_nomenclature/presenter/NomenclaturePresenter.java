package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:28, 15/01/2019.
 */
@InjectViewState
public class NomenclaturePresenter extends BasePresenter<NomenclatureView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private Task task;

    public NomenclaturePresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
        task = TaskContentManager.getInstance().getTask();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();

        disposable.add(RxBus.getInstance().getScanData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addDataFromNfc));
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

    private void addDataFromNfc(String data) {
        Timber.d("SCANNED AND ADD: " + data);
    }

    private void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getNomenclaturesBySO(task.getIdTask()).delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().hideProgress();
                    getViewState().showBaseList(result);
                }, this::handlerErrorsFromBadRequests));
    }
}
