package com.volgagas.personalassistant.data.repository;

import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.mapper.user.UserMapper;
import com.volgagas.personalassistant.models.mapper.user.UserResponseListToUserList;
import com.volgagas.personalassistant.models.mapper.user.UserResponseToUser;
import com.volgagas.personalassistant.models.model.User;

import java.util.List;

import io.reactivex.Single;

public class MainRemoteRepository implements MainRepository {

    private static volatile MainRemoteRepository INSTANCE;

    private static UserMapper userMapper;

    private static UserResponseToUser userResponseToUser;
    private static UserResponseListToUserList userResponseListToUserList;

    private MainRemoteRepository() {
        if (INSTANCE != null) {
            throw new RuntimeException("reflection");
        }
    }

    public static MainRemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MainRemoteRepository.class) {
                if (INSTANCE == null) {
                    userResponseToUser = new UserResponseToUser();
                    userResponseListToUserList = new UserResponseListToUserList();

                    userMapper = new UserMapper(userResponseToUser, userResponseListToUserList);
                    INSTANCE = new MainRemoteRepository();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Single<User> getCardInfo(String numbers) {
        return PersonalAssistant.getBaseApiService().getCardInfo(numbers)
                .map(userMapper::map);
    }

    @Override
    public Single<List<User>> getSearchedUsers() {
        return PersonalAssistant.getBaseApiService().getSearchedUsers()
                .map(userMapper::map);
    }
}
