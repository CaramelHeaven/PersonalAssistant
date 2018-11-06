package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface BaseApiService {
    @GET
    Single<JsonObject> getTest(@Url String url);

    @GET
    Single<JsonObject> getSharePointTest(@Url String url);

    @GET(Constants.MY_HOST + "database/getUserWithPhoto")
    Single<UserResponse> getCardInfo(@Query("userNumbers") String numbers);

    @GET(Constants.MY_HOST + "database/searchUser?")
    Single<List<UserResponse>> getSearchedUsers(@Query("name") String userName);
}
