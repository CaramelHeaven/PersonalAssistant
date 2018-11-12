package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.QueryResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface SPApiService {
    @GET
    Single<QueryResponse> getTest(@Url String url);

    @GET
    Single<QueryResponse> getOpenUniformRequests(@Url String url,
                                                 @QueryMap Map<String, String> options);

    @Headers("Content-Type: application/json;odata=verbose")
    @POST
    Single<Response<Void>> sendTest(@Url String s, @Body JsonObject object);
}
