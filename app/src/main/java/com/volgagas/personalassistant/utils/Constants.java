package com.volgagas.personalassistant.utils;

public class Constants {

    public static final String CLIENT = "68008789-f431-42b1-b4b9-0cdd559674f5";

    //Site
    public static final String SITE_ERP = "volagas.sharepoint.com,9a51e995-62f9-4b40-81c2-d167c4c79182,8603ccc9-1f11-4573-8fa2-140ef4204a1d";

    public static final String MY_HOST = "http://192.168.1.147:8080/pa-volgagas-0.3.6.3/";
    //Lists of this site
    public static final String UNIFORM_REQUESTS_URL = "(guid'895e45dd-17ac-41bd-9a41-3d72bd0cbfc7')";
    public static final String UNIFORM_REQUESTS_TO_USER_URL = "(guid'59C1EE57-5726-4B27-B9DD-B39775E170D5')";

    //main url for requests
    //TEST -- https://volgagas-tstdevaos.sandbox.ax.dynamics.com
    //RELEASE https://volgagas-prod73b403cba6259cd5cedevaos.cloudax.dynamics.com
    public static final String DYNAMICS_365 = "https://volgagas-tstdevaos.sandbox.ax.dynamics.com";
    public static final String GRAPH = "https://volagas.sharepoint.com";
    public static final String SHARE_POINT_DOC_API_WEB = "https://volagas.sharepoint.com/doc/_api/web";
    public static final String SHARE_POINT_API_WEB = "https://volagas.sharepoint.com/_api/web";

    public static final String AUTH_URL = "https://login.microsoftonline.com/common/oauth2/authorize";

    //other
    public static final String REDIRECT_URL = "http://localhost123";
    public static final String EQUIPMENT = "Оборудование";

    //priority for query adapter
    public static final String PRIORITY_NORMAL = "Обычная";
    public static final String PRIORITY_HIGH = "Высокая";
    public static final int IMAGE_COUNT = 1;
}
