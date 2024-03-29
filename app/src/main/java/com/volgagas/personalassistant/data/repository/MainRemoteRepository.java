package com.volgagas.personalassistant.data.repository;

import com.google.gson.JsonObject;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.mapper.common.ApkResponseToApk;
import com.volgagas.personalassistant.models.mapper.common.CommonMapper;
import com.volgagas.personalassistant.models.mapper.info.InfoMapper;
import com.volgagas.personalassistant.models.mapper.info.PersonCertificatesResponseToPersonCertificates;
import com.volgagas.personalassistant.models.mapper.info.PersonDataResponseToPersonData;
import com.volgagas.personalassistant.models.mapper.info.PersonSkillsResponseToPersonSkills;
import com.volgagas.personalassistant.models.mapper.info.SalaryResponseToPersonSalary;
import com.volgagas.personalassistant.models.mapper.kiosk.TaskKioskResponseToTaskTemplate;
import com.volgagas.personalassistant.models.mapper.order.OrderMapper;
import com.volgagas.personalassistant.models.mapper.order.PurchOrderLinesResponseToServerSubOrder;
import com.volgagas.personalassistant.models.mapper.order.PurchReqLinesResponseToUserSubOrder;
import com.volgagas.personalassistant.models.mapper.order.PurchaseOrderToServerOrder;
import com.volgagas.personalassistant.models.mapper.order.PurchaseRequestionResponseToUserOrder;
import com.volgagas.personalassistant.models.mapper.query_template.QueriesTemplateResponseToQueryTemplate;
import com.volgagas.personalassistant.models.mapper.query_template.QueryTemplateMapper;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryResponseToUniformRequest;
import com.volgagas.personalassistant.models.mapper.uniform_request.QueryToUserResponseToQueryToUser;
import com.volgagas.personalassistant.models.mapper.uniform_request.UniformRequestMapper;
import com.volgagas.personalassistant.models.mapper.user.UserDynamicsResponseToUserDynamics;
import com.volgagas.personalassistant.models.mapper.user.UserIdResponseToUserId;
import com.volgagas.personalassistant.models.mapper.user.UserMapper;
import com.volgagas.personalassistant.models.mapper.user.UserResponseListToUserList;
import com.volgagas.personalassistant.models.mapper.user.UserResponseToUser;
import com.volgagas.personalassistant.models.mapper.user.UserSimpleResponseToUserSimple;
import com.volgagas.personalassistant.models.mapper.worker.BarcodeResponseToBarcode;
import com.volgagas.personalassistant.models.mapper.worker.DimMapResponseToNomenclDimension;
import com.volgagas.personalassistant.models.mapper.worker.NomenclatureHostRespToNomenclatureHost;
import com.volgagas.personalassistant.models.mapper.worker.NomenclatureResponseToNomenclature;
import com.volgagas.personalassistant.models.mapper.worker.SubTaskResponseToSubTask;
import com.volgagas.personalassistant.models.mapper.worker.TaskMapper;
import com.volgagas.personalassistant.models.mapper.worker.TaskResponseToTask;
import com.volgagas.personalassistant.models.mapper.worker.TaskResponseToTaskHistory;
import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.models.model.common.Apk;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.model.info.PersonSalary;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.models.model.order.ServerSubOrder;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.model.user.UserSimple;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.NomenclatureDimension;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.DimensionMappingResponse;
import com.volgagas.personalassistant.models.network.user_id.UserId;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.UtilsDateTimeProvider;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class MainRemoteRepository implements MainRepository {

    private static volatile MainRemoteRepository INSTANCE;

    private static UserMapper userMapper;
    private static TaskMapper taskMapper;
    private static QueryTemplateMapper queryMapper;
    private static UniformRequestMapper uniformRequestMapper;
    private static CommonMapper commonMapper;
    private static InfoMapper infoMapper;
    private static OrderMapper orderMapper;

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
                    TaskResponseToTaskHistory taskResponseToTaskHistory = new TaskResponseToTaskHistory();
                    TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate =
                            new TaskKioskResponseToTaskTemplate();
                    UserDynamicsResponseToUserDynamics userDynamicsResponseToUserDynamics =
                            new UserDynamicsResponseToUserDynamics();
                    QueriesTemplateResponseToQueryTemplate queriesTemplateResponseToQueryTemplate =
                            new QueriesTemplateResponseToQueryTemplate();
                    QueryToUserResponseToQueryToUser queryToUserResponseToQueryToUser =
                            new QueryToUserResponseToQueryToUser();
                    UserSimpleResponseToUserSimple userSimpleResponseToUserSimple =
                            new UserSimpleResponseToUserSimple();
                    SubTaskResponseToSubTask subTaskResponseToSubTask =
                            new SubTaskResponseToSubTask();
                    NomenclatureResponseToNomenclature nomenclatureResponseToNomenclature =
                            new NomenclatureResponseToNomenclature();
                    NomenclatureHostRespToNomenclatureHost nomenclatureHostRespToNomenclatureHost =
                            new NomenclatureHostRespToNomenclatureHost();
                    BarcodeResponseToBarcode barcodeResponseToBarcode = new BarcodeResponseToBarcode();
                    ApkResponseToApk apkResponseToApk = new ApkResponseToApk();
                    PersonSkillsResponseToPersonSkills personSkillsResponseToPersonSkills =
                            new PersonSkillsResponseToPersonSkills();
                    PersonCertificatesResponseToPersonCertificates personCertificatesResponseToPersonCertificates =
                            new PersonCertificatesResponseToPersonCertificates();
                    PersonDataResponseToPersonData personDataResponseToPersonData =
                            new PersonDataResponseToPersonData();
                    SalaryResponseToPersonSalary salaryResponseToPersonSalary =
                            new SalaryResponseToPersonSalary();
                    PurchReqLinesResponseToUserSubOrder purchReqLinesResponseToUserSubOrder =
                            new PurchReqLinesResponseToUserSubOrder();
                    PurchaseRequestionResponseToUserOrder purchaseRequestionResponseToUserOrder =
                            new PurchaseRequestionResponseToUserOrder();
                    PurchaseOrderToServerOrder purchaseOrderToServerOrder = new PurchaseOrderToServerOrder();
                    PurchOrderLinesResponseToServerSubOrder purchOrderLinesResponseToServerSubOrder =
                            new PurchOrderLinesResponseToServerSubOrder();
                    DimMapResponseToNomenclDimension dimMapResponseToNomenclDimension =
                            new DimMapResponseToNomenclDimension();

                    //Initial mappers
                    taskMapper = new TaskMapper(taskResponseToTask, taskResponseToTaskHistory,
                            taskKioskResponseToTaskTemplate, subTaskResponseToSubTask,
                            nomenclatureResponseToNomenclature, nomenclatureHostRespToNomenclatureHost,
                            barcodeResponseToBarcode, dimMapResponseToNomenclDimension);
                    userMapper = new UserMapper(userResponseToUser, userResponseListToUserList,
                            userIdResponseToUserId, userDynamicsResponseToUserDynamics,
                            userSimpleResponseToUserSimple);
                    uniformRequestMapper =
                            new UniformRequestMapper(queryResponseToUniformRequest, queryToUserResponseToQueryToUser);
                    queryMapper = new QueryTemplateMapper(queriesTemplateResponseToQueryTemplate);
                    commonMapper = new CommonMapper(apkResponseToApk);
                    infoMapper = new InfoMapper(personCertificatesResponseToPersonCertificates,
                            personSkillsResponseToPersonSkills, personDataResponseToPersonData,
                            salaryResponseToPersonSalary);
                    orderMapper = new OrderMapper(purchReqLinesResponseToUserSubOrder,
                            purchaseRequestionResponseToUserOrder, purchaseOrderToServerOrder,
                            purchOrderLinesResponseToServerSubOrder);

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
    public Single<Nomenclature> findNomenclatureByCardInfo(String data) {
        return PersonalAssistant.getBaseApiService().findNomenclatureCardInfo(data)
                .map(taskMapper::map);
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
        data.put("$filter", "Status eq 'Открыт' and Author/Title eq '" + CacheUser.getUser().getModifiedNormalName() + "'");

        return PersonalAssistant.getSpApiService().getOpenUniformRequestsFromUser(url, data)
                .map(uniformRequestMapper::map);
    }

    @Override
    public Single<List<QueryToUser>> getUniformRequestsToUser() {
        Map<String, String> data = new LinkedHashMap<>();
        String url = Constants.SHARE_POINT_DOC_API_WEB + "/lists" + Constants.UNIFORM_REQUESTS_URL + "/Items?";

        data.put("$select", "Title,Comment,Priority,DueDate,AssignedTo/Title,CategoryLookup0/Title");
        data.put("$expand", "AssignedTo/Id,CategoryLookup0/Title");
        data.put("$filter", "Status eq 'Открыт' and AssignedTo/Title eq '" + CacheUser.getUser().getModifiedNormalName() + "'");

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
    public Single<List<TaskHistory>> getHistoryTasks() {
        String filter = "(AC_Worker eq '" + CacheUser.getUser().getName() + "')";
        String date = " and (AC_ActivityStartDateTime lt " + UtilsDateTimeProvider.formatCurrentDayEnd() + ")";
        String orderBy = "AC_ActivityStartDateTime desc";

        return PersonalAssistant.getBaseApiService().getHistory(filter + date, "50", orderBy)
                .map(taskMapper::mapHistoryTasks);
    }

    @Override
    public Single<List<TaskTemplate>> getTemplateTasks() {
        String url = Constants.DYNAMICS_365 + "/data/SPEntity?$filter=(ProjCategoryId eq '"
                + CacheUser.getUser().getCategory() + "') and (ActivityTypeId eq 'Эталон')";

        return PersonalAssistant.getBaseApiService().getTemplateTasks(url)
                .map(taskMapper::map);
    }

    @Override
    public Single<UserDynamics> getPersonalUserNumber(String personalName) {
        String filter = "(Name eq '" + personalName + "' and WorkerStatus eq " +
                "Microsoft.Dynamics.DataEntities.HcmWorkerStatus'Employed')";

        return PersonalAssistant.getBaseApiService()
                .getPersonalNumber(filter)
                .map(userMapper::map);
    }

    @Override
    public Single<List<Task>> getTasksToday() {
        String filter = "(((AC_ActivityStartDateTime lt " + UtilsDateTimeProvider.formatCurrentDayEnd() + ")" +
                " and (AC_ActivityEndDateTime gt " + UtilsDateTimeProvider.formatCurrentDayEnd() + "))" + " or " +
                " (AC_ActivityStartDateTime lt " + UtilsDateTimeProvider.formatCurrentDayEnd() + " and " +
                "AC_ActivityStartDateTime gt " + UtilsDateTimeProvider.formatLastDayDataFormat() + ") or " +
                "(AC_ActivityEndDateTime lt " + UtilsDateTimeProvider.formatCurrentDayEnd() + " and " +
                "AC_ActivityEndDateTime gt " + UtilsDateTimeProvider.formatLastDayDataFormat() + "))" +
                " and (SO_ServiceStage eq 'Распредел' or SO_ServiceStage eq 'ВРаботе') and (AC_Worker eq '" +
                CacheUser.getUser().getName() + "')";

        return PersonalAssistant.getBaseApiService().getTasksToday(filter)
                .map(taskMapper::mapTasks);
    }

    @Override
    public Single<List<SubTaskViewer>> getSubTasksToday(String serviceOrder) {
        String filter = "(AC_ActivityStartDateTime lt " + UtilsDateTimeProvider.getNextDayDataFormat()
                + ") and (AC_ActivityEndDateTime gt " + UtilsDateTimeProvider.formatLastDayDataFormat() + ")"
                + " and (SO_ServiceOrder eq '" + serviceOrder + "')";

        return PersonalAssistant.getBaseApiService().getSubTasksToday(filter)
                .map(taskMapper::map);
    }

    @Override
    public Single<List<SubTaskViewer>> getSubTasksHistory(String serviceOrder) {
        String filter = "(SO_ServiceOrder eq '" + serviceOrder + "')";

        return PersonalAssistant.getBaseApiService().getSubTasksHistory(filter)
                .map(taskMapper::map);
    }

    @Override
    public Observable<Response<Void>> sendStartedSubTasks(JsonObject object, String idSubTask) {
        String url = Constants.DYNAMICS_365 + "/data/ActivitiesForMobile(dataAreaId='gns',ActivityNumber='" + idSubTask + "')";

        return PersonalAssistant.getBaseApiService().sendStartedSubTasks(url, object);
    }

    @Override
    public Observable<Response<Void>> sendCompletedSubTasks(JsonObject object, String idSubTask) {
        String url = Constants.DYNAMICS_365 + "/data/ActivitiesForMobile(dataAreaId='gns',ActivityNumber='" + idSubTask + "')";

        return PersonalAssistant.getBaseApiService().sendCompletedSubTasks(url, object);
    }

    @Override
    public Observable<Response<Void>> sendCanceledSubTasks(JsonObject object, String idSubTask) {
        String url = Constants.DYNAMICS_365 + "/data/ActivitiesForMobile(dataAreaId='gns',ActivityNumber='" + idSubTask + "')";

        return PersonalAssistant.getBaseApiService().sendCanceledSubTasks(url, object);
    }

    @Override
    public Observable<Response<Void>> sendImageToDynamics(JsonObject object) {
        String url = Constants.DYNAMICS_365 + "/data/ActivitiesPictures";

        return PersonalAssistant.getBaseApiService().sendImageToDynamics(url, object);
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
    public Single<List<Nomenclature>> getNomenclaturesBySO(String soId) {
        String filter = "TransactionSubType eq Microsoft.Dynamics.DataEntities." +
                "SMATransactionSubType'Consumption' and ServiceOrderId eq '" + soId + "'";

        return PersonalAssistant.getBaseApiService().getNomenclatures(filter)
                .map(taskMapper::map);
    }

    @Override
    public Single<List<NewOrder>> getOrderNewBase() {
        List<NewOrder> stringList = new ArrayList<>();

        stringList.add(new NewOrder("Ботинки летние", R.drawable.ic_summer_shoe));
        stringList.add(new NewOrder("Брюки летние", R.drawable.ic_summer_pants));
        stringList.add(new NewOrder("Куртка летняя", R.drawable.ic_summer_denim_jacket));
        stringList.add(new NewOrder("Полукомбинизон летний", R.drawable.ic_summer_overall));

        return Single.just(stringList);
    }

    @Override
    public Single<List<NewOrder>> getOrderNewAdditionally() {
        List<NewOrder> stringList = new ArrayList<>();

        stringList.add(new NewOrder("Костюм для защиты эл. дуги", R.drawable.ic_protect_electrons));
        stringList.add(new NewOrder("Костюм зимний", R.drawable.ic_trench_coat));
        stringList.add(new NewOrder("Костюм сварщика", R.drawable.ic_welder));
        stringList.add(new NewOrder("Куртка зимняя", R.drawable.ic_coat));
        stringList.add(new NewOrder("Подшлеммник зимний", R.drawable.ic_summer_denim_jacket));
        stringList.add(new NewOrder("Сапоги зимние", R.drawable.ic_winter_boot));
        stringList.add(new NewOrder("Сапоги резиновые", R.drawable.ic_footwear));
        stringList.add(new NewOrder("Тапочки-туфли", R.drawable.ic_slipper));
        stringList.add(new NewOrder("Термобелье", R.drawable.ic_pijama));
        stringList.add(new NewOrder("Футболка", R.drawable.ic_summer_denim_jacket));
        stringList.add(new NewOrder("Шапка вязаная", R.drawable.ic_cap));

        return Single.just(stringList);
    }

    @Override
    public Single<List<Order>> getUserOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("Title", true, "description"));
        orderList.add(new Order("Title", true, "description"));
        orderList.add(new Order("Title", true, "description"));
        orderList.add(new Order("Title", true, "description"));

        return Single.just(orderList);
    }

    @Override
    public Single<UserSimple> getUserPhotoByName(String userName) {
        return PersonalAssistant.getBaseApiService().getUserSimpleByName(userName)
                .map(userMapper::map);
    }

    @Override
    public Single<PersonData> getInfoAboutUserFromDynamics() {
        String filter = "PartyNumber eq '" + CacheUser.getUser().getPersonalDynamics365Number() + "'";

        return PersonalAssistant.getBaseApiService().getPersonData(filter)
                .map(infoMapper::map);
    }

    @Override
    public Single<Barcode> getBarcodeInfoFromServer(String barcodeNumbers) {
        String url = Constants.DYNAMICS_365 + "/data/ItemsBarcode(dataAreaId='gns',itemBarCode='" +
                barcodeNumbers + "')";

        return PersonalAssistant.getBaseApiService().getBarcodeByString(url)
                .map(taskMapper::map);
    }

    @Override
    public Single<ResponseBody> downloadNewestApk(String apkName) {
        String url = "https://volagas.sharepoint.com/sites/msteams_33574c/_api/Web/GetFileByServerRelativePath" +
                "(decodedurl='/sites/msteams_33574c/Shared Documents/Мобильное приложение/ПО/" + apkName + "')/$value";

        return PersonalAssistant.getSpApiService().downloadNewestApk(url);
    }

    @Override
    public Single<List<Apk>> getCurrentListApkes() {
        String url = "https://volagas.sharepoint.com/sites/msteams_33574c/_api/Web/GetFolderByServerRelativeUrl" +
                "('/sites/msteams_33574c/Shared Documents/Мобильное приложение/ПО')/Files";

        return PersonalAssistant.getSpApiService().getListOfApkes(url)
                .map(commonMapper::map);
    }

    @Override
    public Observable<Response<Void>> attachNomenclatureToServiceOrder(JsonObject object) {
        String url = Constants.DYNAMICS_365 + "/data/SOLinesEntity/";

        return PersonalAssistant.getBaseApiService().createNomenclatureInServiceOrder(url, object);
    }

    @Override
    public Observable<Response<Void>> updateNomenclatureInServer(String serviceOrderId, int serviceOrderLineNum,
                                                                 JsonObject object) {
        String url = Constants.DYNAMICS_365 + "/data/SOLinesEntity(dataAreaId='gns', " +
                "ServiceOrderId='" + serviceOrderId + "',ServiceOrderLineNum=" + serviceOrderLineNum + ")";

        return PersonalAssistant.getBaseApiService().updateNomenclatureInServer(url, object);
    }

    @Override
    public Single<List<Contract>> getContractsForUser() {
        return Single.just(new ArrayList<>());
    }

    @Override
    public Single<List<PersonCertificates>> getPersonCertificates() {
        String filter = "PartyNumber eq '" + CacheUser.getUser().getWorkerRecId() + "'";

        return PersonalAssistant.getBaseApiService().getPersonCetrificates(filter)
                .map(infoMapper::map);
    }

    @Override
    public Single<List<PersonSkills>> getPersonSkills() {
        String filter = "PartyNumber eq '" + CacheUser.getUser().getWorkerRecId() + "'";

        return PersonalAssistant.getBaseApiService().getPersonSkills(filter)
                .map(infoMapper::map);
    }

    @Override
    public Single<PersonSalary> getPersonSalary() {
        String filter = "PersonnelNumber eq '" + CacheUser.getUser().getPersonalDynamics365Number() + "'";

        return PersonalAssistant.getBaseApiService().getPersonSalary(filter)
                .map(infoMapper::map);
    }

    @Override
    public Single<List<UserOrder>> getPurchaseRequisitions() {
        //todo change д000000310 on user personnel number. this is keep in cache user model
        String filter = "(PreparerPersonnelNumber eq 'д000000310')";

        return PersonalAssistant.getBaseApiService().getPurchaseRequisition(filter, "DefaultRequestedDate desc")
                .map(orderMapper::map);
    }

    @Override
    public Single<List<UserSubOrder>> getPurchaseRequisitionLines(String requisitionNumber) {
        String filter = "RequisitionNumber eq '" + requisitionNumber + "'";

        return PersonalAssistant.getBaseApiService()
                .getPurchaseRequisitionLines(filter, "RequisitionLineNumber asc")
                .map(orderMapper::map);
    }

    @Override
    public Single<List<ServerOrder>> getPurchaseOrders() {
        String filter = "(OrdererPersonnelNumber eq 'д000000310')";

        return PersonalAssistant.getBaseApiService().getPurchaseOrders(filter, "AccountingDate desc")
                .map(orderMapper::map);
    }

    @Override
    public Single<List<ServerSubOrder>> getPurchaseOrderLines(String orderNumber) {
        String filter = "PurchaseOrderNumber eq '" + orderNumber + "'";

        return PersonalAssistant.getBaseApiService().getPurchaseOrderLines(filter, "LineNumber asc")
                .map(orderMapper::map);
    }

    @Override
    public Single<NomenclatureDimension> getNomenclatureMappingDimension(String category) {
        String filter = "ProjCategoryEmplId eq '" + category + "'";

        return PersonalAssistant.getBaseApiService().getDimensionMappingByServiceOrder(filter)
                .map(taskMapper::map);
    }
}
