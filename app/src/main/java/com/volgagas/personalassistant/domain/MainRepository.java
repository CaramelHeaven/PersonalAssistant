package com.volgagas.personalassistant.domain;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.model.QueryTemplate;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.network.user_id.UserId;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

public interface MainRepository {

    /* Information from mifare card. Send to own small server and get user or equipment data
     * */
    Single<User> getCardInfo(String numbers);

    /* Get all users from special folders from SCUD
     * */
    Single<List<User>> getSearchedUsers();

    Single<List<UniformRequest>> getUniformRequestsFromUser();

    /* User Id from D365
     * */
    Observable<UserId> getUserIdByUserName(String name);

    Single<Response<Void>> createUniformQueryItem(JsonObject jsonObject);

    /* GET history tasks from D365 for special user
     * */
    Single<List<Task>> getHistory();

    /* GET tasks today
     * */
    Single<List<Task>> getTasksToday();

    /* Subtasks for special task.
     * */
    Single<List<SubTaskViewer>> getSubTasksToday(String serviceOrder);

    /* Subtasks for special task in history
     * */
    Single<List<SubTaskViewer>> getSubTasksHistory(String serviceOrder);

    /* Patch tasks with field - started
     * */
    Observable<Response<Void>> sendStartedSubTasks(JsonObject object, String idSubTask);

    /* Patch tasks with field - completed
     * */
    Observable<Response<Void>> sendCompletedSubTasks(JsonObject object, String idSubTask);

    /* Patch tasks with field - canceled
     * */
    Observable<Response<Void>> sendCanceledSubTasks(JsonObject object, String idSubTask);

    /*
     * Send created templates tasks for user.
     * */
    Observable<Response<Void>> sendTemplateTasks(String query, JsonObject object);

    /* Get templates for selected one of it in the choose_category folder
     * */
    Single<List<QueryTemplate>> getTemplatesQueries();

    Single<List<Task>> getTemplateTasks();

    Single<UserDynamics> getPersonalUserNumber(String personalName);

    List<Task> testedData();
}
