package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.network.UserIdResponse;
import com.volgagas.personalassistant.models.network.user_id.UserId;

/**
 * Created by CaramelHeaven on 12:33, 15.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserIdResponseToUserId extends Mapper<UserIdResponse, UserId> {
    @Override
    public UserId map(UserIdResponse value) {
        UserId userId = new UserId();
        fillData(userId, value);
        return userId;
    }

    @Override
    protected void fillData(UserId userId, UserIdResponse userIdResponse) {
        userId.setId(userIdResponse.getValue().get(0).getId());
    }
}
