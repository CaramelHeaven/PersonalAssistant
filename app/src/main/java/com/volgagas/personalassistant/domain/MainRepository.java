package com.volgagas.personalassistant.domain;

import com.volgagas.personalassistant.models.model.User;

import io.reactivex.Single;

public interface MainRepository {

    Single<User> getCardInfo(String numbers);
}
