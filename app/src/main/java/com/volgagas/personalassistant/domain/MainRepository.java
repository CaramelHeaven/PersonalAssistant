package com.volgagas.personalassistant.domain;

import com.google.gson.JsonObject;
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

    Single<User> getCardInfo(String numbers);

    Single<List<User>> getSearchedUsers();

    Single<List<UniformRequest>> getUniformRequestsFromUser();

    Observable<UserId> getUserIdByUserName(String name);

    Single<Response<Void>> createUniformQueryItem(JsonObject jsonObject);

    Single<List<Task>> getTemplateTasks();

    Single<UserDynamics> getPersonalUserNumber(String personalName);

    List<Task> testedData();
}
