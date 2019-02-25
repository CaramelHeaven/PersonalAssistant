package com.volgagas.personalassistant.domain;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.model.user.UserSimple;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.user_id.UserId;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface MainRepository {

    /**
     * Information from mifare card. Send to own small server and get user or equipment data
     *
     * @param numbers - from NFC
     * @return user data [or equipment]
     */
    Single<User> getCardInfo(String numbers);

    /**
     * Get nomenclature data from the scanned current card.
     *
     * @param data = from NFC
     * @return nomenclature data
     */
    Single<Nomenclature> findNomenclatureByCardInfo(String data);

    /**
     * Get all users from our mini server
     *
     * @return list of all users inside company
     */
    Single<List<User>> getSearchedUsers();

    /**
     * Queries from user in SharePoint
     *
     * @return list if queries
     */
    Single<List<UniformRequest>> getUniformRequestsFromUser();

    /**
     * Queries to user in SharePoint
     *
     * @return list of queries
     */
    Single<List<QueryToUser>> getUniformRequestsToUser();

    /**
     * Get user id from D365
     *
     * @param name - user name which contains two words. First Name and Last name. We get this name
     *             after login via card and get info from our mini server -> then [modified] current
     *             name (change words) and send to d365 to get user id
     * @return UserId which can help grab data to current user
     */
    Observable<UserId> getUserIdByUserName(String name);

    /**
     * Create uniform query (заявку) to the SharePoint
     *
     * @param jsonObject - object which contains data for cra
     */
    Single<Response<Void>> createUniformQueryItem(JsonObject jsonObject);

    /**
     * GET history tasks from D365 for special user
     */
    Single<List<TaskHistory>> getHistoryTasks();

    /**
     * Get current tasks for today
     *
     * @return list of tasks which we reflect on the WorkerTodayFragment screen
     */
    Single<List<Task>> getTasksToday();

    /**
     * Get activities for current tasks
     *
     * @param serviceOrder - important id for activities which subscribers onto current ServiceOrder
     * @return list of activities (means from D365)
     */
    Single<List<SubTaskViewer>> getSubTasksToday(String serviceOrder);

    /**
     * See above example
     *
     * @param serviceOrder - id
     * @return list of activities
     */
    Single<List<SubTaskViewer>> getSubTasksHistory(String serviceOrder);

    /**
     * Update tasks time
     *
     * @param object    - contains data for update activities
     * @param idSubTask - contains id activity
     * @return response from server
     */
    Observable<Response<Void>> sendStartedSubTasks(JsonObject object, String idSubTask);

    /**
     * Send to inform server that we completed our activities work
     *
     * @param object    - data
     * @param idSubTask - current id activity
     * @return response from network
     */
    Observable<Response<Void>> sendCompletedSubTasks(JsonObject object, String idSubTask);

    /**
     * Patch tasks with field - canceled
     */
    Observable<Response<Void>> sendCanceledSubTasks(JsonObject object, String idSubTask);

    /**
     * another kek
     */
    Observable<Response<Void>> sendImageToDynamics(JsonObject object);

    /**
     * Send created templates tasks for user.
     *
     * @param query - query for create each task on the server
     */
    Observable<Response<Void>> sendTemplateTasks(String query, JsonObject object);

    /**
     * Get templates for selected one of it in the choose_category folder
     */
    Single<List<QueryTemplate>> getTemplatesQueries();

    Single<List<TaskTemplate>> getTemplateTasks();

    /**
     * Personal number which help us to get UserDynamics data for create template tasks!
     *
     * @param personalName - personal number
     */
    Single<UserDynamics> getPersonalUserNumber(String personalName);

    /**
     * data for order new base fragment
     */
    Single<List<NewOrder>> getOrderNewBase();

    /**
     * data for order new additionally fragment
     */
    Single<List<NewOrder>> getOrderNewAdditionally();

    /**
     * User orders from share point. Contains clothes, boots etc.
     */
    Single<List<Order>> getUserOrders();

    /**
     * GET user preview photo by name for messenger chat
     *
     * @param userName - user name.
     */
    Single<UserSimple> getUserPhotoByName(String userName);

    /**
     * Get all nomenclatures for current Service Order
     *
     * @param soId - service order id
     */
    Single<List<Nomenclature>> getNomenclaturesBySO(String soId);

    /**
     * Get information about user from D365.
     *
     * @param personD365Id - id d365
     * @return data of user information
     */
    Single<PersonData> getInfoAboutUserFromDynamics(String personD365Id);

    /**
     * @param barcodeNumbers - scanned string data from NomenclatureBarcodeActivity
     */
    Single<Barcode> getBarcodeInfoFromServer(String barcodeNumbers);

    /**
     * Download apk file
     *
     * @param apkName - apk name file, newest
     */
    Single<ResponseBody> downloadNewestApk(String apkName);


    /**
     * Get current list of apkes from SharePoint disk (it reference to Microsoft Teams)
     *
     * @return list of all apkes which folder contains
     */
    Single<List<Apk>> getCurrentListApkes();

    /**
     * Create new nomenclature
     *
     * @param object - for create nomenclature
     * @return response from server
     */
    Observable<Response<Void>> attachNomenclatureToServiceOrder(JsonObject object);

    /**
     * Update nomenclatures fields in D365
     *
     * @param serviceOrderId      - current service order id
     * @param serviceOrderLineNum - line of nomenclature which we want to update
     * @param object              - json data contains current quantity for update SO id.
     * @return response of successful or not successful result
     */
    Observable<Response<Void>> updateNomenclatureInServer(String serviceOrderId, int serviceOrderLineNum,
                                                          JsonObject object);

    /**
     * Get contracts for user from D365
     *
     * @return list of contracts
     */
    Single<List<Contract>> getContractsForUser();

    /**
     * Get person certificates which saved on server d365
     *
     * @param personD365Id - current person Id
     * @return list of certificates
     */
    Single<List<PersonCertificates>> getPersonCertificates(String personD365Id);

    /**
     * Get person skills from d365
     *
     * @param personD365Id - person id
     * @return list of skills
     */
    Single<List<PersonSkills>> getPersonSkills(String personD365Id);
}
