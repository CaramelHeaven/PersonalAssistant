package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class NomenclaturePresenter extends BasePresenter<NomenclatureView> {

    private MainRepository repository;
    private Task task;
    private List<Nomenclature> notChangedNomenclature; // used for compare original list with changed our
    // list and if each nomenclature have difference count - we post it
    private List<Nomenclature> updatedListNomenclature; // helper list for update UI and handler data
    // from scanned barcodes

    public NomenclaturePresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
        task = TaskContentManager.getInstance().getTask();
        notChangedNomenclature = new ArrayList<>();
        updatedListNomenclature = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(RxBus.getInstance().getScanData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addDataFromNfc, this::handlerErrorsFromBadRequests));

        disposable.add(RxBus.getInstance().getSubscribeToUpdateToken()
                .filter(result -> result.equals(Constants.WORKER_NOMENCLATURE_PRESENTER))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> loadData()));

        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.CLOSED_NOMENCLATURE_BARCODE_ACTIVITY))
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Timber.d("CHECKING CALLED ECCH TIME: " + s);
                    }
                })
                .flatMap((Function<String, ObservableSource<List<Nomenclature>>>) s ->
                        mappingBarcodesToNomenclatures())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().addedBarcodeNomenclaturesToBaseList(result)));

        loadData();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }

    /**
     * Map comes barcodeList from Scanned Barcode Activity and mapping it to nomenclatures
     */
    private Observable<List<Nomenclature>> mappingBarcodesToNomenclatures() {
        List<Barcode> barcodeList = (List<Barcode>) (Object)
                CachePot.getInstance().getCacheBarcodeList();
        List<Nomenclature> nomenclatureList = new ArrayList<>();

        //mapping barcode to nomenclature
        for (Barcode barcode : barcodeList) {
            Nomenclature nomenclature = new
                    Nomenclature(barcode.getBarcodeName(), barcode.getCount(), "шт");
            nomenclatureList.add(nomenclature);
        }

        //add nomenclature to common updated list or increase count if it exist
        for (Nomenclature data : nomenclatureList) {
            if (updatedListNomenclature.contains(data)) {
                Nomenclature nom = updatedListNomenclature.get(updatedListNomenclature.indexOf(data));
                nom.setCount(data.getCount() + nom.getCount());
            } else {
                updatedListNomenclature.add(data);
            }
        }

        return Observable.just(updatedListNomenclature);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_NOMENCLATURE_PRESENTER);
        } else {
            Timber.d("THROWABLE: " + throwable.getMessage());
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    /**
     * Request to get nomenclatures from own server
     *
     * @param data - codekey for spesial nomenclature
     */
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

    /**
     * Load base nomenclatures from ServiceOrder Id
     */
    protected void loadData() {
        getViewState().showProgress();
        disposable.add(repository.getNomenclaturesBySO(task.getIdTask())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    notChangedNomenclature.addAll(result);
                    updatedListNomenclature.addAll(notChangedNomenclature);

                    getViewState().hideProgress();
                    getViewState().showBaseList(result);
                }, this::handlerErrorsFromBadRequests));
    }

    public Task getTask() {
        return task;
    }

    public void clearNotChangedList() {
        notChangedNomenclature.clear();
    }

    public void clearUpdatedList() {
        updatedListNomenclature.clear();
    }

    /**
     * @param ourList - list from adapter. Compare it with origin list
     */
    public void compareNomenclatures(List<Nomenclature> ourList) {
        Timber.d("non: " + notChangedNomenclature.toString());
        Timber.d("our lIST: " + ourList.toString());
        List<Nomenclature> result = new ArrayList<>();



        Timber.d("out list after: " + ourList.toString());
    }
}
