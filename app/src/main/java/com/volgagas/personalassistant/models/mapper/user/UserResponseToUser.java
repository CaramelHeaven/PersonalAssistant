package com.volgagas.personalassistant.models.mapper.user;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.UserResponse;

public class UserResponseToUser extends Mapper<UserResponse, User> {
    @Override
    public User map(UserResponse value) {
        User user = new User();
        fillData(user, value);

        return user;
    }

    @Override
    protected void fillData(User user, UserResponse response) {
        user.setCategory(response.getCategory());
        user.setCodekeyList(response.getCodekeyList());
        user.setLastEntered(response.getLastEntered());
        user.setName(response.getName());
        user.setPosition(response.getJob());
        user.setUserImage(response.getUserImage());
    }
}
