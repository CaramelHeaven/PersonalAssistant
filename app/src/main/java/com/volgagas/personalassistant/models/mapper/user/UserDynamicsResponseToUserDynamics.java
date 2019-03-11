package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.network.UserDynamicsResponse;

/**
 * Created by CaramelHeaven on 17:50, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserDynamicsResponseToUserDynamics extends Mapper<UserDynamicsResponse, UserDynamics> {
    @Override
    public UserDynamics map(UserDynamicsResponse value) {
        UserDynamics user = new UserDynamics();
        fillData(user, value);
        return user;
    }

    @Override
    protected void fillData(UserDynamics user, UserDynamicsResponse value) {
        //used 0 cause we get only 1 person
        if (value.getValue().size() > 0) {
            user.setPersonalNumber(value.getValue().get(0).getPersonnelNumber());
            user.setWorkerRecId(value.getValue().get(0).getWorkerRecId());
        } else {
            user.setPersonalNumber("");
            user.setWorkerRecId("");
        }
    }
}
