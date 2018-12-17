package com.volgagas.personalassistant.data.repository;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.mapper.query_template.QueriesTemplateResponseToQueryTemplate;
import com.volgagas.personalassistant.models.mapper.query_template.QueryTemplateMapper;
import com.volgagas.personalassistant.models.mapper.task.TaskResponseToTask;
import com.volgagas.personalassistant.models.mapper.task.TasksMapper;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryResponseToUniformRequest;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryToUserResponseToQueryToUser;
import com.volgagas.personalassistant.models.mapper.uniform_request.UniformRequestMapper;
import com.volgagas.personalassistant.models.mapper.user.UserDynamicsResponseToUserDynamics;
import com.volgagas.personalassistant.models.mapper.user.UserIdResponseToUserId;
import com.volgagas.personalassistant.models.mapper.user.UserMapper;
import com.volgagas.personalassistant.models.mapper.user.UserResponseListToUserList;
import com.volgagas.personalassistant.models.mapper.user.UserResponseToUser;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.network.user_id.UserId;
import com.volgagas.personalassistant.utils.Constants;

import org.json.JSONObject;

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
    private static QueryTemplateMapper queryMapper;
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
                    QueriesTemplateResponseToQueryTemplate queriesTemplateResponseToQueryTemplate =
                            new QueriesTemplateResponseToQueryTemplate();
                    QueryToUserResponseToQueryToUser queryToUserResponseToQueryToUser =
                            new QueryToUserResponseToQueryToUser();

                    //Initial mappers
                    tasksMapper = new TasksMapper(taskResponseToTask);
                    userMapper = new UserMapper(userResponseToUser, userResponseListToUserList,
                            userIdResponseToUserId, userDynamicsResponseToUserDynamics);
                    uniformRequestMapper =
                            new UniformRequestMapper(queryResponseToUniformRequest, queryToUserResponseToQueryToUser);
                    queryMapper = new QueryTemplateMapper(queriesTemplateResponseToQueryTemplate);

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

        return PersonalAssistant.getSpApiService().getOpenUniformRequestsFromUser(url, data)
                .map(uniformRequestMapper::map);
    }

    @Override
    public Single<List<QueryToUser>> getUniformRequestsToUser() {
        Map<String, String> data = new LinkedHashMap<>();
        String url = Constants.SHARE_POINT_DOC_API_WEB + "/lists" + Constants.UNIFORM_REQUESTS_URL + "/Items?";

        data.put("$select", "Title,Comment,Priority,DueDate,AssignedTo/Title");
        data.put("$expand", "AssignedTo/Id");
        data.put("$filter", "Status eq 'Открыт' and AssignedTo/Title eq '" + "Татьяна Нехорошкова" + "'");

        return PersonalAssistant.getSpApiService().getOpenUniformRequestsToUser(url, data)
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
    public Single<List<Task>> getHistory() {
        return null;
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
    public Single<List<Task>> getTasksToday() {
        String filter = "(AC_ActivityStartDateTime lt " + PersonalAssistant.getNextDayDataFormat()
                + " and AC_ActivityEndDateTime gt " + PersonalAssistant.getLastDayDataFormat()
                + ") and (SO_ServiceStage eq 'Распредел' or SO_ServiceStage eq 'ВРаботе') and (AC_Worker eq '"
                + CacheUser.getUser().getName() + "')";

        return PersonalAssistant.getBaseApiService().getTasksToday(filter)
                .map(tasksMapper::map);
    }

    @Override
    public Single<List<SubTaskViewer>> getSubTasksToday(String serviceOrder) {
        return null;
    }

    @Override
    public Single<List<SubTaskViewer>> getSubTasksHistory(String serviceOrder) {
        return null;
    }

    @Override
    public Observable<Response<Void>> sendStartedSubTasks(JsonObject object, String idSubTask) {
        return null;
    }

    @Override
    public Observable<Response<Void>> sendCompletedSubTasks(JsonObject object, String idSubTask) {
        return null;
    }

    @Override
    public Observable<Response<Void>> sendCanceledSubTasks(JsonObject object, String idSubTask) {
        return null;
    }

    @Override
    public Observable<Response<Void>> sendTemplateTasks(String query, JsonObject object) {
        String url = Constants.DYNAMICS_365 + "/data/SPEntity" + query;

        return PersonalAssistant.getBaseApiService().sendTemplateTasks(url, object);
    }

    @Override
    public Single<List<QueryTemplate>> getTemplatesQueries() {
        String url = "https://volagas.sharepoint.com/doc/_api/web/lists(guid'59C1EE57-5726-4B27-B9DD-B39775E170D5')/Items?$select=Title, id";

        return PersonalAssistant.getSpApiService().getTemplateQueries(url)
                .map(queryMapper::map);
    }

    @Override
    public List<Task> testedData() {
        List<Task> array = new ArrayList<>();
        Task task = new Task();
        task.setDescription("fasf");
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);
        array.add(task);

        return array;
    }
}
