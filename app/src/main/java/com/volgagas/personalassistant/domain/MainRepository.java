package com.volgagas.personalassistant.domain;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface MainRepository {

    Single<User> getCardInfo(String numbers);

    Single<List<User>> getSearchedUsers();

    Single<List<UniformRequest>> getUniformRequestsFromUser();

    Observable<JsonObject> getUserIdByUserName(String name);
}
