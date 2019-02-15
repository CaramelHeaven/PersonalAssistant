package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.QueriesTemplateResponse;
import com.volgagas.personalassistant.models.network.QueryResponse;
import com.volgagas.personalassistant.models.network.QueryToUserResponse;
import com.volgagas.personalassistant.models.network.UserIdResponse;
import com.volgagas.personalassistant.utils.Constants;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface SPApiService {

    @GET
    Single<QueryResponse> getOpenUniformRequestsFromUser(@Url String url,
                                                         @QueryMap Map<String, String> options);

    @GET
    Single<QueryToUserResponse> getOpenUniformRequestsToUser(@Url String url,
                                                             @QueryMap Map<String, String> options);

    @Headers("Content-Type: application/json;odata=verbose")
    @POST(Constants.SHARE_POINT_DOC_API_WEB + "/lists" + Constants.UNIFORM_REQUESTS_URL + "/items")
    Single<Response<Void>> createUniformQueryItem(@Body JsonObject object);

    @GET(Constants.SHARE_POINT_API_WEB + "/siteusers?")
    Observable<UserIdResponse> getUserIdByUserName(@Query("$filter") String title,
                                                   @Query("$select") String select);

    @GET
    Single<QueriesTemplateResponse> getTemplateQueries(@Url String url);

    @GET
    Single<ResponseBody> downloadNewestApk(@Url String url);
}
