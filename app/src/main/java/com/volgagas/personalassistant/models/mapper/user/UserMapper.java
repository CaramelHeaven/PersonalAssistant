package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.UserResponse;

public class UserMapper {
    private final UserResponseToUser userResponseToUser;

    public UserMapper(UserResponseToUser userResponseToUser) {
        this.userResponseToUser = userResponseToUser;
    }

    public User map(UserResponse response) {
        return userResponseToUser.map(response);
    }
}
