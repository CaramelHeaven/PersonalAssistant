package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.crashlytics.android.Crashlytics;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
import com.volgagas.personalassistant.utils.services.CreateNomenclaturesWorker;
import com.volgagas.personalassistant.utils.services.UpdateNomenclaturesWorker;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    // used for compare original list with changed our list and if each nomenclature have difference count - we update it
    private List<Nomenclature> helperNomenclatureList;
    private List<Nomenclature> originalList;

    public NomenclaturePresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
        task = TaskContentManager.getInstance().getTask();

        helperNomenclatureList = new ArrayList<>();
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

        //Action from close ActivityBarcode and showData to adapter
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.CLOSED_NOMENCLATURE_BARCODE_ACTIVITY))
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
                    Nomenclature(barcode.getBarcodeName(), barcode.getCount(), barcode.getUnit());
            nomenclature.setItemBarCode(barcode.getItemBarCode());

            nomenclatureList.add(nomenclature);
        }

        //add nomenclature to common updated list or increase count if it exist
        for (Nomenclature data : nomenclatureList) {
            if (helperNomenclatureList.contains(data)) {
                Nomenclature nom = helperNomenclatureList.get(helperNomenclatureList.indexOf(data));
                nom.setCount(data.getCount() + nom.getCount());
            } else {
                helperNomenclatureList.add(data);
            }
        }

        Timber.d("helper list after barcode scanned: " + helperNomenclatureList);
        Timber.d("checking original List: " + originalList);
        return Observable.just(helperNomenclatureList);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        if (throwable.getMessage().contains(Constants.HTTP_401)) {
            RxBus.getInstance().passActionForUpdateToken(Constants.WORKER_NOMENCLATURE_PRESENTER);
        } else {
            sendCrashlytics(throwable);
        }
    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    /**
     * Request to get nomenclatures from own server
     *
     * @param data - code-key for special nomenclature
     */
    private void addDataFromNfc(String data) {
        Timber.d("add to nomenclature");
        disposable.add(repository.findNomenclatureByCardInfo(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.getName().equals("")) {
                        getViewState().errorNomenclature();
                    } else {
                        getViewState().addNomenclatureToBaseList(result);
                    }
                }, throwable -> {
                    Crashlytics.logException(throwable);
                    getViewState().errorNomenclature();
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
                    if (helperNomenclatureList.size() > 0) {
                        helperNomenclatureList.clear();
                    }

                    helperNomenclatureList.addAll(result);

                    toOriginalList(result);
                    CachePot.getInstance().setOriginalList(originalList);

                    getViewState().hideProgress();
                    getViewState().showBaseList(result);
                }, this::handlerErrorsFromBadRequests));
    }

    public Task getTask() {
        return task;
    }

    public void clearHelperList() {
        helperNomenclatureList.clear();
    }

    public void clearOriginalList() {
        originalList.clear();
    }

    public void createNomenclatures(List<Nomenclature> ourList) {
        Timber.d("our lIST: " + ourList.toString());
        Timber.d("original list: " + originalList);
        Timber.d("helper list: " + helperNomenclatureList);

        originalList = CachePot.getInstance().getOriginalList();

        List<Nomenclature> createResult = new ArrayList<>();
        List<Nomenclature> updateResult = new ArrayList<>();

        for (Nomenclature ourData : ourList) {
            //not bad, yey! Add to updateList if value in nomenclature has changed
            for (Nomenclature kek : originalList) {
                if (ourData.getName().equals(kek.getName()) && ourData.getCount() != kek.getCount()) {
                    updateResult.add(ourData);
                }
            }

            if (!originalList.contains(ourData)) {
                createResult.add(ourData);
            }
        }
        Timber.d("checking created list: " + createResult);
        Timber.d("checking updatedList: " + updateResult);

        if (createResult.size() > 0) {
            CachePot.getInstance().putCreateNomenclatures(createResult);
            OneTimeWorkRequest createWorker = new OneTimeWorkRequest.Builder(CreateNomenclaturesWorker.class)
                    .setConstraints(new Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    .setInputData(new Data.Builder()
                            .putString("SERVICE_ORDER_ID", task.getIdTask())
                            .putString("PROJ_CATEGORY_ID", task.getProjCategoryId())
                            .putString("SERVICE_TASK_ID", task.getServiceTaskId())
                            .putString("SO_PROJ_ID", task.getSoProjId())
                            .build())
                    .build();

            WorkManager.getInstance()
                    .enqueue(createWorker);
        }
        if (updateResult.size() > 0) {
            CachePot.getInstance().putUpdateNomenclatures(updateResult);
            OneTimeWorkRequest updateWorker = new OneTimeWorkRequest.Builder(UpdateNomenclaturesWorker.class)
                    .setConstraints(new Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build())
                    .setInputData(new Data.Builder()
                            .putString("SERVICE_ORDER_ID", task.getIdTask())
                            .build())
                    .build();

            WorkManager.getInstance()
                    .enqueue(updateWorker);
        }
    }

    public void setHelperNomenclatureList(List<Nomenclature> helperNomenclatureList) {
        this.helperNomenclatureList.clear();
        this.helperNomenclatureList.addAll(helperNomenclatureList);
    }

    private void toOriginalList(List<Nomenclature> result) {
        originalList = new ArrayList<>();

        for (Nomenclature data : result) {
            Nomenclature nomenclature = new
                    Nomenclature(data.getName(), data.getCount(), data.getUnit());
            nomenclature.setItemBarCode(data.getItemBarCode());

            originalList.add(nomenclature);
        }
    }

    public List<Nomenclature> getOriginalList() {
        return originalList;
    }
}
