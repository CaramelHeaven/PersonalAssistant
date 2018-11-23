package com.volgagas.personalassistant.data.repository;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.mapper.task.TaskResponseToTask;
import com.volgagas.personalassistant.models.mapper.task.TasksMapper;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryResponseToUniformRequest;
import com.volgagas.personalassistant.models.mapper.uniform_request.UniformRequestMapper;
import com.volgagas.personalassistant.models.mapper.user.UserDynamicsResponseToUserDynamics;
import com.volgagas.personalassistant.models.mapper.user.UserIdResponseToUserId;
import com.volgagas.personalassistant.models.mapper.user.UserMapper;
import com.volgagas.personalassistant.models.mapper.user.UserResponseListToUserList;
import com.volgagas.personalassistant.models.mapper.user.UserResponseToUser;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.network.user_id.UserId;
import com.volgagas.personalassistant.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

public class MainRemoteRepository implements MainRepository {

    private static volatile MainRemoteRepository INSTANCE;

    private static UserMapper userMapper;
    private static TasksMapper tasksMapper;
    private static UniformRequestMapper uniformRequestMapper;

    private MainRemoteRepository() {
        if (INSTANCE != null) {
            throw new RuntimeException("reflection");
        }
    }

    public static MainRemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MainRemoteRepository.class) {
                if (INSTANCE == null) {
                    UserResponseToUser userResponseToUser = new UserResponseToUser();
                    UserResponseListToUserList userResponseListToUserList = new UserResponseListToUserList();
                    QueryResponseToUniformRequest queryResponseToUniformRequest = new QueryResponseToUniformRequest();
                    UserIdResponseToUserId userIdResponseToUserId = new UserIdResponseToUserId();
                    TaskResponseToTask taskResponseToTask = new TaskResponseToTask();
                    UserDynamicsResponseToUserDynamics userDynamicsResponseToUserDynamics = new UserDynamicsResponseToUserDynamics();

                    tasksMapper = new TasksMapper(taskResponseToTask);
                    userMapper = new UserMapper(userResponseToUser, userResponseListToUserList,
                            userIdResponseToUserId, userDynamicsResponseToUserDynamics);
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
        String url = Constants.SHARE_POINT_DOC_API_WEB + "/lists" + Constants.UNIFORM_REQUESTS_URL + "/Items?";

        data.put("$select", "Title,Author,Comment,Priority,DueDate,LastText/Name,Author/Title");
        data.put("$expand", "Author/Id");
        data.put("$filter", "Status eq 'Открыт' and Author/Title eq '" + "Татьяна Нехорошкова" + "'");

        return PersonalAssistant.getSpApiService().getOpenUniformRequests(url, data)
                .map(uniformRequestMapper::map);
    }

    @Override
    public Observable<UserId> getUserIdByUserName(String name) {
        String filter = "Title eq '" + name + "'";
        return PersonalAssistant.getSpApiService().getUserIdByUserName(filter, "id")
                .map(userMapper::map);
    }

    @Override
    public Single<Response<Void>> createUniformQueryItem(JsonObject jsonObject) {
        return PersonalAssistant.getSpApiService().createUniformQueryItem(jsonObject);
    }

    @Override
    public Single<List<Task>> getTemplateTasks() {
        return null;
    }

    @Override
    public Single<UserDynamics> getPersonalUserNumber(String personalName) {
        String filter = "Name eq '" + personalName + "'";
        return PersonalAssistant.getBaseApiService()
                .getPersonalNumber(filter)
                .map(userMapper::map);
    }

    @Override
    public List<Task> testedData() {
        List<Task> array = new ArrayList<>();
        array.add(new Task("ytjtyfffff"));
        array.add(new Task("afdbfffff"));
        array.add(new Task("vasfvffff"));
        array.add(new Task("asgfffff"));
        array.add(new Task("asdffffff"));
        array.add(new Task("fsdfffff"));
        array.add(new Task("ffgdgffff"));
        array.add(new Task("1241fffff"));
        array.add(new Task("125fffff"));
        array.add(new Task("f411ffff"));

        return array;
    }
}
