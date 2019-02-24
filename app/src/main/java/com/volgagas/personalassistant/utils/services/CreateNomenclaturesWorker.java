package com.volgagas.personalassistant.utils.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;

import java.util.Arrays;
import java.util.List;

import androidx.work.RxWorker;
import androidx.work.WorkerParameters;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:45, 15/02/2019.
 */
public class CreateNomenclaturesWorker extends RxWorker {
    private MainRepository repository;

    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public CreateNomenclaturesWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        repository = MainRemoteRepository.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Result> createWork() {
        List<Nomenclature> nomenclatureList = CachePot.getInstance().getCreateList();
        String serviceOrderId = getInputData().getString("SERVICE_ORDER_ID");
        String projCategoryId = getInputData().getString("PROJ_CATEGORY_ID");

        return Single.just(nomenclatureList)
                .flattenAsObservable((Function<List<Nomenclature>, Iterable<Nomenclature>>) data -> data)
                .flatMap((Function<Nomenclature, ObservableSource<Response<Void>>>) data -> repository
                        .attachNomenclatureToServiceOrder(mappingToJson(data, serviceOrderId, projCategoryId)))
                .toList()
                .map(responses -> Result.success())
                .doOnError(throwable -> {
                    Timber.d("THROWABLE: " + throwable.getMessage());
                    Crashlytics.logException(throwable);
                    Result.retry();
                });
    }

    private JsonObject mappingToJson(Nomenclature nomenclature, String serviceOrderId, String projCategory) {
        JsonObject object = new JsonObject();

        object.add("dataAreaId", new JsonPrimitive("gns"));
        object.add("ServiceOrderId", new JsonPrimitive(serviceOrderId));
        object.add("ItemId", new JsonPrimitive(nomenclature.getName()));
        object.add("InventDimId", new JsonPrimitive("GNS-000627"));
        object.add("ProjLinePropertyId", new JsonPrimitive("Расход"));
        object.add("ProjCategoryId", new JsonPrimitive(projCategory + "_ТМЦ"));
        object.add("DateRangeFrom", new JsonPrimitive("2019-02-22T12:00:00Z"));
        object.add("Qty", new JsonPrimitive(nomenclature.getCount()));
        object.add("DefaultDimension", new JsonPrimitive(Long.parseLong("5637144586")));//still here
        object.add("TransactionSubType", new JsonPrimitive("Consumption"));
        object.add("DateExecution", new JsonPrimitive("2019-02-22T12:00:00Z"));
        object.add("ProjCurrencyCode", new JsonPrimitive("RUB"));
        object.add("TransactionType", new JsonPrimitive("Item"));
        object.add("DateRangeTo", new JsonPrimitive("2019-02-22T12:00:00Z"));
        object.add("ServiceTaskId", new JsonPrimitive("БудОстанов"));
        object.add("Worker", new JsonPrimitive(Long.parseLong("5637144586")));
        object.add("Unit", new JsonPrimitive(nomenclature.getUnit()));

        return object;
    }
}
