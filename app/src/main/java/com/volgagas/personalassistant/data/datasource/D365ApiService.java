package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface D365ApiService {
    @GET
    Single<JsonObject> getTest(@Url String url);
}
