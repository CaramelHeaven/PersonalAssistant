package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.user.UserSimple;
import com.volgagas.personalassistant.models.network.UserSimpleResponse;

/**
 * Created by CaramelHeaven on 17:15, 09/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserSimpleResponseToUserSimple extends Mapper<UserSimpleResponse, UserSimple> {
    @Override
    public UserSimple map(UserSimpleResponse value) {
        UserSimple userSimple = new UserSimple();
        fillData(userSimple, value);

        return userSimple;
    }

    @Override
    protected void fillData(UserSimple userSimple, UserSimpleResponse userSimpleResponse) {
        userSimple.setId(userSimpleResponse.getId());
        userSimple.setName(userSimpleResponse.getName());
        userSimple.setPhoto(userSimpleResponse.getPreviewPhoto());
    }
}
