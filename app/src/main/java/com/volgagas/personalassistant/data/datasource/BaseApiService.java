package com.volgagas.personalassistant.data.datasource;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.network.BarcodeResponse;
import com.volgagas.personalassistant.models.network.NomenclatureHostResponse;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.UserDynamicsResponse;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.models.network.UserSimpleResponse;
import com.volgagas.personalassistant.utils.Constants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    /**
     * Provide history tasks which user has been completed
     * Take the last 50 tasks.
     */
    @GET("data/SOWithAC?")
    Single<TaskResponse> getHistory(@Query("$filter") String filter,
                                    @Query("$top") String count,
                                    @Query("$orderby") String orderBy);

    /**
     * Create template tasks from kiosk for user
     *
     * @param url    - url which contains activity order reference
     * @param object - object where we ...
     */
    @PATCH
    Observable<Response<Void>> sendTemplateTasks(@Url String url, @Body JsonObject object);

    /**
     * Send sub tasks for field started when user used mobile NFC from card on the factory
     *
     * @param url    - url which contains activity order reference
     * @param object - object where we put field activity id
     */
    @PATCH
    Observable<Response<Void>> sendStartedSubTasks(@Url String url, @Body JsonObject object);

    @POST
    Observable<Response<Void>> sendImageToDynamics(@Url String url, @Body JsonObject object);

    /**
     * Get Template tasks from Kiosk.
     *
     * @param url - current url where we can get our tasks
     */
    @GET
    Single<TaskKioskResponse> getTemplateTasks(@Url String url);

    @PATCH
    Observable<Response<Void>> sendCompletedSubTasks(@Url String url, @Body JsonObject object);

    @PATCH
    Observable<Response<Void>> sendCanceledSubTasks(@Url String url, @Body JsonObject object);

    Single<BarcodeResponse> getBarcodeByString(String barcodeResult);
}
