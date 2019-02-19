package com.volgagas.personalassistant.utils.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

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
public class SendNomenclaturesToServerWorker extends RxWorker {
    private MainRepository repository;

    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public SendNomenclaturesToServerWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        repository = MainRemoteRepository.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Result> createWork() {
        List<Nomenclature> nomenclatureList = (List<Nomenclature>) (Object) CachePot.getInstance().getCacheBarcodeList();
        CachePot.getInstance().clearCacheBarcodeList();

        String serviceOrderId = getInputData().getString("SERVICE_ORDER_ID");

        return Single.just(nomenclatureList)
                .flattenAsObservable((Function<List<Nomenclature>, Iterable<Nomenclature>>) data -> data)
                .flatMap((Function<Nomenclature, ObservableSource<Response<Void>>>) data -> repository
                        .attachNomenclatureToServiceOrder(mappingToJson(data, serviceOrderId)))
                .toList()
                .map(responses -> Result.success())
                .doOnError(throwable -> {
                    Timber.d("THROWABLE: " + throwable.getMessage());
                    Result.retry();
                });
    }

    private JsonObject mappingToJson(Nomenclature nomenclature, String serviceOrderId) {
        JsonObject object = new JsonObject();

        object.add("ServiceOrderId", new JsonPrimitive(serviceOrderId));
        object.add("ProjectCategoryId", new JsonPrimitive("Электрики_ТМЦ"));
        object.add("Qty", new JsonPrimitive(nomenclature.getCount()));
        object.add("dataAreaId", new JsonPrimitive("gns"));
        object.add("ItemId", new JsonPrimitive(nomenclature.getName()));
        object.add("DataRangeTo", new JsonPrimitive("2018-01-01T00:00:00Z"));
        object.add("DataRangeFrom", new JsonPrimitive("2018-01-01T04:03:00Z"));

        Timber.d("CHECK JSON: " + object.toString());

        return object;
    }
}
