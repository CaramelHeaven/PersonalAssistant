package com.volgagas.personalassistant.presentation.worker_nomenclature.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.crashlytics.android.Crashlytics;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.NomenclatureDimension;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.UtilsDateTimeProvider;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
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
    private String inventDimId; // string for create nomenclature

    private List<Nomenclature> createResult;
    private List<Nomenclature> updateResult;

    private boolean isLoadNomenclaturesFromServer = true;

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
    }

    public void presenterLoadData() {
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

    public Boolean createNomenclatures(List<Nomenclature> ourList) {
        originalList = CachePot.getInstance().getOriginalList();

        createResult = new ArrayList<>();
        updateResult = new ArrayList<>();

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
        if (createResult.size() > 0 || updateResult.size() > 0) {
            return true;
        }
        return false;
    }

    public void sendNomenclaturesToServer() {
        if (createResult.size() > 0) {
            disposable.add(repository.getNomenclatureMappingDimension(task.getProjCategoryId())
                    .subscribeOn(Schedulers.io())
                    .flatMap((Function<NomenclatureDimension, SingleSource<List<Nomenclature>>>) nomenclatureDimension -> {
                        inventDimId = nomenclatureDimension.getInventDimId();
                        return Single.just(createResult);
                    })
                    .flattenAsObservable((Function<List<Nomenclature>, Iterable<Nomenclature>>) data -> data)
                    .flatMap((Function<Nomenclature, ObservableSource<Response<Void>>>) data -> repository
                            .attachNomenclatureToServiceOrder(mappingToCreateJson(
                                    data, task.getIdTask(), task.getProjCategoryId(), task.getServiceTaskId(), task.getSoProjId())))
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        if (updateResult.size() > 0) {
                            updateNomenclatures(updateResult);
                        } else {
                            getViewState().acceptAndCloseView();
                        }
                    }, throwable -> {
                        Timber.d("th: " + throwable);
                        sendCrashlytics(throwable);
                    }));
        } else if (updateResult.size() > 0) {
            updateNomenclatures(updateResult);
        }
    }

    private void updateNomenclatures(List<Nomenclature> updateResult) {
        disposable.add(Single.just(updateResult)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<Nomenclature>, Iterable<Nomenclature>>) data -> data)
                .flatMap((Function<Nomenclature, ObservableSource<Response<Void>>>) nomenclature ->
                        repository.updateNomenclatureInServer(task.getIdTask(), nomenclature.getServiceOrderLineNum(),
                                mappingToUpdateJson(nomenclature.getCount())))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().acceptAndCloseView(), throwable -> {
                    Timber.d("throwable: " + throwable);
                    Crashlytics.logException(throwable);
                }));
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

    private JsonObject mappingToCreateJson(Nomenclature nomenclature, String serviceOrderId, String projCategory,
                                           String serviceTaskId, String soProjId) {
        JsonObject object = new JsonObject();

        object.add("dataAreaId", new JsonPrimitive("gns"));
        object.add("ServiceOrderId", new JsonPrimitive(serviceOrderId));
        object.add("ItemId", new JsonPrimitive(nomenclature.getName()));
        object.add("InventDimId", new JsonPrimitive(inventDimId));
        object.add("ProjLinePropertyId", new JsonPrimitive("Расход"));
        object.add("ProjCategoryId", new JsonPrimitive(projCategory + "_ТМЦ"));
        object.add("ProjId", new JsonPrimitive(soProjId));
        object.add("ToInventDimId", new JsonPrimitive("AllBlank"));
        object.add("DateRangeFrom",
                new JsonPrimitive(UtilsDateTimeProvider.workerServiceTime() + "T12:00:00Z"));
        object.add("Qty", new JsonPrimitive(nomenclature.getCount()));
        object.add("DefaultDimension", new JsonPrimitive(Long.parseLong("5637144586")));//still here
        object.add("TransactionSubType", new JsonPrimitive("Consumption"));
        object.add("DateExecution",
                new JsonPrimitive(UtilsDateTimeProvider.workerServiceTime() + "T12:00:00Z"));
        object.add("ProjCurrencyCode", new JsonPrimitive("RUB"));
        object.add("TransactionType", new JsonPrimitive("Item"));
        object.add("DateRangeTo", new JsonPrimitive(UtilsDateTimeProvider.getCurrentFormatDateTime()));
        object.add("ServiceTaskId", new JsonPrimitive(serviceTaskId));
        object.add("Worker", new JsonPrimitive(Long.parseLong(CacheUser.getUser().getWorkerRecId())));
        object.add("Unit", new JsonPrimitive(nomenclature.getUnit()));

        return object;
    }

    private JsonObject mappingToUpdateJson(int qty) {
        JsonObject object = new JsonObject();
        object.add("Qty", new JsonPrimitive(qty));

        return object;
    }

    public List<Nomenclature> getCreateResult() {
        return createResult;
    }

    public List<Nomenclature> getUpdateResult() {
        return updateResult;
    }

    public boolean isLoadNomenclaturesFromServer() {
        return isLoadNomenclaturesFromServer;
    }

    public void setLoadNomenclaturesFromServer(boolean loadNomenclaturesFromServer) {
        isLoadNomenclaturesFromServer = loadNomenclaturesFromServer;
    }
}
