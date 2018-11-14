package com.volgagas.personalassistant.data.repository;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryResponseToUniformRequest;
import com.volgagas.personalassistant.models.mapper.uniform_request.UniformRequestMapper;
import com.volgagas.personalassistant.models.mapper.user.UserMapper;
import com.volgagas.personalassistant.models.mapper.user.UserResponseListToUserList;
import com.volgagas.personalassistant.models.mapper.user.UserResponseToUser;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.utils.Constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MainRemoteRepository implements MainRepository {

    private static volatile MainRemoteRepository INSTANCE;

    private static UserMapper userMapper;
    private static UniformRequestMapper uniformRequestMapper;

    private static UserResponseToUser userResponseToUser;
    private static UserResponseListToUserList userResponseListToUserList;
    private static QueryResponseToUniformRequest queryResponseToUniformRequest;

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
                    queryResponseToUniformRequest = new QueryResponseToUniformRequest();

                    userMapper = new UserMapper(userResponseToUser, userResponseListToUserList);
                    uniformRequestMapper = new UniformRequestMapper(queryResponseToUniformRequest);
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

    @Override
    public Single<List<UniformRequest>> getUniformRequestsFromUser() {
        Map<String, String> data = new LinkedHashMap<>();
        String url = Constants.SHARE_POINT_WEB_API + "/lists" + Constants.UNIFORM_REQUESTS_URL + "/Items?";

        data.put("$select", "Title,Author,Comment,Priority,DueDate,LastText/Name,Author/Title");
        data.put("$expand", "Author/Id");
        data.put("$filter", "Status eq 'Открыт' and Author/Title eq '" + "Татьяна Нехорошкова" + "'");

        return PersonalAssistant.getSpApiService().getOpenUniformRequests(url, data)
                .map(uniformRequestMapper::map);
    }

    @Override
    public Observable<JsonObject> getUserIdByUserName(String name) {
        String url = "https://volagas.sharepoint.com/doc/_api/web/siteusers?&$filter=Title eq 'Бунькин Дмитрий'&$select=id";
        return PersonalAssistant.getSpApiService().getUserIdByUserName(url);
    }
}
