package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.UserResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 13:01, 06.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserResponseListToUserList {

    public List<User> map(List<UserResponse> value) {
        List<User> userList = new ArrayList<>();
        fillData(userList, value);
        return userList;
    }

    private void fillData(List<User> users, List<UserResponse> responseList) {
        for (UserResponse response : responseList) {
            User user = new User();
            user.setCategory(response.getCategory());
            user.setCodekey(response.getCodekey());
            user.setLastEntered(response.getLastEntered());
            user.setName(response.getName());
            user.setPosition(response.getJob());
            user.setUserImage(response.getUserImage());
            users.add(user);
        }
    }
}
