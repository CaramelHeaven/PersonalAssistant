package com.volgagas.personalassistant.utils.services;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;

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
 * Created by CaramelHeaven on 10:17, 21/02/2019.
 */
public class UpdateNomenclaturesWorker extends RxWorker {
    private MainRepository repository;

    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public UpdateNomenclaturesWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @Override
    public Single<Result> createWork() {
        String serviceOrderId = getInputData().getString("SERVICE_ORDER_ID");

        repository = MainRemoteRepository.getInstance();
        List<Nomenclature> updateList = CachePot.getInstance().getUpdateList();
        return Single.just(updateList)
                .flattenAsObservable((Function<List<Nomenclature>, Iterable<Nomenclature>>) data -> data)
                .flatMap((Function<Nomenclature, ObservableSource<Response<Void>>>) nomenclature ->
                        repository.updateNomenclatureInServer(serviceOrderId, nomenclature.getServiceOrderLineNum(),
                                mappingToJson(nomenclature.getCount())))
                .toList()
                .map(responses -> Result.success())
                .doOnError(throwable -> {
                    Timber.d("THROWABLE: " + throwable.getMessage());
                    Crashlytics.logException(throwable);
                    Result.retry();
                });
    }

    private JsonObject mappingToJson(int qty) {
        JsonObject object = new JsonObject();
        object.add("Qty", new JsonPrimitive(qty));

        return object;
    }
}
