package com.volgagas.personalassistant;

import android.app.Application;

import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.volgagas.personalassistant.data.datasource.BaseApiService;
import com.volgagas.personalassistant.data.datasource.SPApiService;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class PersonalAssistant extends Application {

    private static String lastTokenDynamics365 = "";
    private static String lastTokenSharePoint = "";

    private static BaseApiService baseApiService;
    private static SPApiService spApiService;

    public static String getLastTokenDynamics365() {
        return lastTokenDynamics365;
    }

    /* Times
     * */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'", Locale.getDefault());
    private static SimpleDateFormat patternFromServer = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        TaskContentManager.getInstance();
    }

    public static BaseApiService getBaseApiService() {
        return baseApiService;
    }

    public static SPApiService getSpApiService() {
        return spApiService;
    }

    public static void provideDynamics365Auth(String token, String url) {
        lastTokenDynamics365 = token;

        OkHttpClient.Builder builderWithAuth = initBuilderAuth(lastTokenDynamics365, "D365");
        OkHttpClient client = builderWithAuth.build();
        Retrofit retrofit;
        if (url.length() > 0) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.DYNAMICS_365)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        baseApiService = retrofit.create(BaseApiService.class);
    }

    public static void provideSharePointAuth(String token) {
        lastTokenSharePoint = token;

        OkHttpClient.Builder builderWithAuth = initBuilderAuth(lastTokenSharePoint, "SP");
        OkHttpClient client = builderWithAuth.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GRAPH)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        spApiService = retrofit.create(SPApiService.class);
        Timber.d("share point init completed");
    }

    private static OkHttpClient.Builder initBuilderAuth(String token, String authTo) {
        switch (authTo) {
            case "D365":
                OkHttpClient.Builder builderD365 = new OkHttpClient().newBuilder();
                builderD365.readTimeout(15, TimeUnit.SECONDS);
                builderD365.connectTimeout(10, TimeUnit.SECONDS);

                builderD365.addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                });
                HttpLoggingInterceptor interceptorD365 = new HttpLoggingInterceptor();

                interceptorD365.setLevel(HttpLoggingInterceptor.Level.BODY);
                builderD365.addInterceptor(interceptorD365);

                return builderD365;
            case "SP":
                OkHttpClient.Builder builderSP = new OkHttpClient().newBuilder();
                builderSP.readTimeout(10, TimeUnit.SECONDS);
                builderSP.connectTimeout(7, TimeUnit.SECONDS);

                builderSP.addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(request);
                });
                HttpLoggingInterceptor interceptorSP = new HttpLoggingInterceptor();

                interceptorSP.setLevel(HttpLoggingInterceptor.Level.BODY);
                builderSP.addInterceptor(interceptorSP);

                return builderSP;
        }
        return null;
    }

    public static SimpleDateFormat getPatternFromServer() {
        return patternFromServer;
    }

    public static String getCurrentDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = dateFormat.format(Calendar.getInstance().getTime());

        return result + "23:59:59Z";
    }

}
