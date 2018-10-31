package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SPApiService {
    @GET
    Single<JsonObject> getTest(@Url String url);

    @GET
    Single<JsonObject> getTest2(@Url String s);
}
