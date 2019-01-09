package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.model.user.UserSimple;
import com.volgagas.personalassistant.models.network.UserDynamicsResponse;
import com.volgagas.personalassistant.models.network.UserIdResponse;
import com.volgagas.personalassistant.models.network.UserResponse;
import com.volgagas.personalassistant.models.network.UserSimpleResponse;
import com.volgagas.personalassistant.models.network.user_id.UserId;

import java.util.List;

public class UserMapper {
    private final UserResponseToUser userResponseToUser;
    private final UserResponseListToUserList userResponseListToUserList;
    private final UserIdResponseToUserId userIdResponseToUserId;
    private final UserDynamicsResponseToUserDynamics userDynamicsResponseToUserDynamics;
    private final UserSimpleResponseToUserSimple userSimpleResponseToUserSimple;

    public UserMapper(UserResponseToUser userResponseToUser, UserResponseListToUserList userResponseListToUserList,
                      UserIdResponseToUserId userIdResponseToUserId,
                      UserDynamicsResponseToUserDynamics userDynamicsResponseToUserDynamics,
                      UserSimpleResponseToUserSimple userSimpleResponseToUserSimple) {
        this.userResponseToUser = userResponseToUser;
        this.userResponseListToUserList = userResponseListToUserList;
        this.userIdResponseToUserId = userIdResponseToUserId;
        this.userDynamicsResponseToUserDynamics = userDynamicsResponseToUserDynamics;
        this.userSimpleResponseToUserSimple = userSimpleResponseToUserSimple;
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

    public UserDynamics map(UserDynamicsResponse response) {
        return userDynamicsResponseToUserDynamics.map(response);
    }

    public UserSimple map(UserSimpleResponse response) {
        return userSimpleResponseToUserSimple.map(response);
    }
}
