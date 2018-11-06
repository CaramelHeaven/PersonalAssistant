package com.volgagas.personalassistant.domain;

import com.volgagas.personalassistant.models.model.User;

import java.util.List;

import io.reactivex.Single;

public interface MainRepository {

    Single<User> getCardInfo(String numbers);

    Single<List<User>> getSearchedUsers(String name);
}
