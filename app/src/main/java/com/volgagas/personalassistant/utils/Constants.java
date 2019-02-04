package com.volgagas.personalassistant.utils;

public class Constants {

    public static final String CLIENT = "68008789-f431-42b1-b4b9-0cdd559674f5";

    //Site
    public static final String SITE_ERP = "volagas.sharepoint.com,9a51e995-62f9-4b40-81c2-d167c4c79182,8603ccc9-1f11-4573-8fa2-140ef4204a1d";

    public static final String MY_HOST = "http://192.168.1.147:8080/pa-volgagas-0.3.6.3/";
    //Lists of this site
    public static final String UNIFORM_REQUESTS_URL = "(guid'895e45dd-17ac-41bd-9a41-3d72bd0cbfc7')";
    public static final String UNIFORM_REQUESTS_TO_USER_URL = "(guid'59C1EE57-5726-4B27-B9DD-B39775E170D5')";

    //MAIN reference. It works on many place. Don't ignore this
    public static String DYNAMICS_365 = "https://volgagas-testdevaos.sandbox.ax.dynamics.com";
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
}
