package com.volgagas.personalassistant.domain;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.network.user_id.UserId;

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

    Single<List<Task>> getTemplateTasks();

    Single<UserDynamics> getPersonalUserNumber(String personalName);

    List<Task> testedData();
}
