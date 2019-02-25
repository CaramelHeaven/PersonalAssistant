package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.BarcodeResponse;
import com.volgagas.personalassistant.models.network.NomenclatureHostResponse;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;
import com.volgagas.personalassistant.models.network.PersonCertificatesResponse;
import com.volgagas.personalassistant.models.network.PersonDataResponse;
import com.volgagas.personalassistant.models.network.PersonSkillsResponse;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.UserDynamicsResponse;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.models.network.UserSimpleResponse;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface BaseApiService {

    @GET(Constants.MY_HOST + "database/findUser")
    Single<UserResponse> getCardInfo(@Query("userNumbers") String numbers);

    @GET(Constants.MY_HOST + "database/nomenclature")
    Single<NomenclatureHostResponse> findNomenclatureCardInfo(@Query("numbers") String numbers);

    @GET(Constants.MY_HOST + "database/getAllUsers")
    Single<List<UserResponse>> getSearchedUsers();

    @GET(Constants.MY_HOST + "database/getUserPhotoByName")
    Single<UserSimpleResponse> getUserSimpleByName(@Query("userName") String userName);

    @GET("data/BaseWorkers?")
    Single<UserDynamicsResponse> getPersonalNumber(@Query("$filter") String name);

    @GET("data/SOWithAC?")
    Single<TaskResponse> getTasksToday(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<SubTaskResponse> getSubTasksToday(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<SubTaskResponse> getSubTasksHistory(@Query("$filter") String filter);

    @GET("data/SOLinesEntity?")
    Single<NomenclatureResponse> getNomenclatures(@Query("$filter") String filter);

    @GET("data/SOWithAC?")
    Single<TaskResponse> getHistory(@Query("$filter") String filter,
                                    @Query("$top") String count,
                                    @Query("$orderby") String orderBy);

    @PATCH
    Observable<Response<Void>> sendTemplateTasks(@Url String url, @Body JsonObject object);

    @PATCH
    Observable<Response<Void>> sendStartedSubTasks(@Url String url, @Body JsonObject object);

    @POST
    Observable<Response<Void>> sendImageToDynamics(@Url String url, @Body JsonObject object);

    @GET
    Single<TaskKioskResponse> getTemplateTasks(@Url String url);

    @PATCH
    Observable<Response<Void>> sendCompletedSubTasks(@Url String url, @Body JsonObject object);

    @PATCH
    Observable<Response<Void>> sendCanceledSubTasks(@Url String url, @Body JsonObject object);

    @GET()
    Single<BarcodeResponse> getBarcodeByString(@Url String url);

    @POST
    Observable<Response<Void>> createNomenclatureInServiceOrder(@Url String url, @Body JsonObject object);

    @PATCH
    Observable<Response<Void>> updateNomenclatureInServer(@Url String url, @Body JsonObject object);

    @GET("data/PersonCertificates")
    Single<PersonCertificatesResponse> getPersonCetrificates(@Query("$filter") String partyNumber);

    @GET("data/PersonSkills")
    Single<PersonSkillsResponse> getPersonSkills(@Query("$filter") String partyNumber);

    @GET("data/Workers")
    Single<PersonDataResponse> getPersonData(@Query("$filter") String partyNumber);
}
