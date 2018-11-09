package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.QueryResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface SPApiService {
    @GET
    Single<QueryResponse> getTest(@Url String url);

    @GET
    Single<QueryResponse> getOpenUniformRequests(@Url String url,
                                                 @QueryMap Map<String, String> options);

    @GET
    Single<JsonObject> getTest2(@Url String s);
}
