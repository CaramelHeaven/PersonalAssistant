package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.UserIdResponse;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.models.network.user_id.UserId;

import java.util.List;

public class UserMapper {
    private final UserResponseToUser userResponseToUser;
    private final UserResponseListToUserList userResponseListToUserList;
    private final UserIdResponseToUserId userIdResponseToUserId;

    public UserMapper(UserResponseToUser userResponseToUser, UserResponseListToUserList userResponseListToUserList,
                      UserIdResponseToUserId userIdResponseToUserId) {
        this.userResponseToUser = userResponseToUser;
        this.userResponseListToUserList = userResponseListToUserList;
        this.userIdResponseToUserId = userIdResponseToUserId;
    }

    public User map(UserResponse response) {
        return userResponseToUser.map(response);
    }

    public List<User> map(List<UserResponse> responseList) {
        return userResponseListToUserList.map(responseList);
    }

    public UserId map(UserIdResponse response) {
        return userIdResponseToUserId.map(response);
    }
}
