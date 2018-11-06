package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.UserResponse;

import java.util.List;

public class UserMapper {
    private final UserResponseToUser userResponseToUser;
    private final UserResponseListToUserList userResponseListToUserList;

    public UserMapper(UserResponseToUser userResponseToUser, UserResponseListToUserList userResponseListToUserList) {
        this.userResponseToUser = userResponseToUser;
        this.userResponseListToUserList = userResponseListToUserList;
    }

    public User map(UserResponse response) {
        return userResponseToUser.map(response);
    }

    public List<User> map(List<UserResponse> responseList) {
        return userResponseListToUserList.map(responseList);
    }
}
