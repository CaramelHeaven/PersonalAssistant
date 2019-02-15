package com.volgagas.personalassistant.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Constants {

    public static final String CLIENT = "68008789-f431-42b1-b4b9-0cdd559674f5";

    //Site
    public static final String SITE_ERP = "volagas.sharepoint.com,9a51e995-62f9-4b40-81c2-d167c4c79182,8603ccc9-1f11-4573-8fa2-140ef4204a1d";

    public static final String MY_HOST = "http://192.168.1.147:8080/volgagas-0.4/";
    //Lists of this site
    public static final String UNIFORM_REQUESTS_URL = "(guid'895e45dd-17ac-41bd-9a41-3d72bd0cbfc7')";
    public static final String UNIFORM_REQUESTS_TO_USER_URL = "(guid'59C1EE57-5726-4B27-B9DD-B39775E170D5')";

    //MAIN reference. It works on many place. Don't ignore this
    public static String DYNAMICS_365 = "https://volgagas-testdevaos.sandbox.ax.dynamics.com";

    //MAIN OTHER REFERENCES
    public static final String GRAPH = "https://volagas.sharepoint.com";
    public static final String SHARE_POINT_DOC_API_WEB = "https://volagas.sharepoint.com/doc/_api/web";
    public static final String SHARE_POINT_API_WEB = "https://volagas.sharepoint.com/_api/web";

    //references for shared preference inside setting activity
    public static final String DYNAMICS_TEST = "https://volgagas-testdevaos.sandbox.ax.dynamics.com";
    public static final String DYNAMICS_PROD = "https://volgagas-prod81363c7256ed48a071bdevaos.cloudax.dynamics.com";
    public static final String DYNAMICS_FAKE_URL = "https://volgagas-tstlalala.sandbox.ax.dynamics.com";

    public static final String AUTH_URL = "https://login.microsoftonline.com/common/oauth2/authorize";

    //other
    public static final String REDIRECT_URL = "http://localhost123";
    public static final String EQUIPMENT = "Оборудование";

    //priority for query adapter
    public static final String PRIORITY_NORMAL = "Обычная";
    public static final String PRIORITY_HIGH = "Высокая";
    public static final int IMAGE_COUNT = 1;

    // HTTP RESPONSES
    public static final int HTTP_400 = 400;
    public static final int HTTP_204 = 204;

    //errors
    public static final String HTTP_401 = "401";

    public static final String SP_USER_PREFERENCE = "USER_DATA"; // base shared preference data

    //shared preferences fields
    public static final String SP_CURRENT_HTTP = "CURRENT_HTTP";
    public static final String SP_ENABLE_FUNCTIONS = "ENABLE_FUNCTIONS";
    public static final String SP_D365_USER_CACHE = "D365_USER_CACHE";
    public static final String SP_SHARE_POINT_USER_CACHE = "SP_SHARE_POINT_USER_CACHE";

    //other
    public static final String ADD_MORE_NOMENCLATURES = "ADD_MORE_NOMENCLATURES";
    public static final String USUAL = "USUAL";
    public static final String VIEW_IS_COLLAPSED = "VIEW_IS_COLLAPSED";
    public static final String EXPANDED = "EXPANDED";
    public static final String COLLAPSED = "COLLAPSED";
    public static final String REQUEST_DATA_FROM_BARCODE_LIST = "REQUEST_DATA_FROM_BARCODE_LIST";
    public static final String PASS_DATA_BARCODE = "PASS_DATA_BARCODE";
    public static final String UPDATE_DATA_BARCODE = "UPDATE_DATA_BARCODE";
    public static final String CLOSED_NOMENCLATURE_BARCODE_ACTIVITY = "CLOSED_NOMENCLATURE_BARCODE_ACTIVITY";
    public static final String NOMENCLATURE_DF_CLOSE = "NOMENCLATURE_DF_CLOSE";

    //refreshing key-word for each screen
    public static final String WORKER_TODAY_NEW_PRESENTER = "WORKER_TODAY_NEW_PRESENTER";
    public static final String WORKER_GPA_PRESENTER = "WORKER_GPA_PRESENTER";
    public static final String WORKER_RESULT_PRESENTER = "WORKER_RESULT_PRESENTER";
    public static final String WORKER_NOMENCLATURE_PRESENTER = "WORKER_NOMENCLATURE_PRESENTER";
    public static final String WORKER_HISTORY_PRESENTER = "WORKER_HISTORY_PRESENTER";
    public static final String PROJECTS_UNIFORM_PRESENTER = "PROJECTS_UNIFORM_PRESENTER";
    public static final String PROJECTS_QUERY_TO_USER_PRESENTER = "PROJECTS_QUERY_TO_USER_PRESENTER";
    public static final String WORKER_HISTORY_COMES_DATA = "WORKER_HISTORY_COMES_DATA";
    public static final String UPDATE_TOKEN_SILENT = "UPDATE_TOKEN_SILENT";
    public static final String QUERY_CREATE_CHOOSE_CATEGORY = "QUERY_CREATE_CHOOSE_CATEGORY";
    public static final String QUERY_CREATE_WHO_IS_THE_RECIPIENT = "QUERY_CREATE_WHO_IS_RECIPIENT";

    public static final List<String> ALLOW_PEOPLE_TESTING = new ArrayList<>(Arrays.asList("Бунькин", "Тарновский", "Иванов"));
}
