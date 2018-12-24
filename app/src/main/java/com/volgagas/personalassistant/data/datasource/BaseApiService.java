package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.UserDynamicsResponse;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface BaseApiService {
    @GET
    Single<JsonObject> getTest(@Url String url);

    @GET
    Single<JsonObject> getSharePointTest(@Url String url);

    @GET(Constants.MY_HOST + "database/findUser")
    Single<UserResponse> getCardInfo(@Query("userNumbers") String numbers);

    @GET(Constants.MY_HOST + "database/getAllUsers")
    Single<List<UserResponse>> getSearchedUsers();

    @GET("data/BaseWorkers?")
    Single<UserDynamicsResponse> getPersonalNumber(@Query("$filter") String name);

    @GET("data/SOWithAC?")
    Single<TaskResponse> getTasksToday(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<SubTaskResponse> getSubTasksToday(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<SubTaskResponse> getSubTasksHistory(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<TaskResponse> getHistory(@Query("$filter") String filter,
                                    @Query("$top") String count,
                                    @Query("$orderby") String orderBy);

    @PATCH
    Observable<Response<Void>> sendTemplateTasks(@Url String url, @Body JsonObject object);
}
