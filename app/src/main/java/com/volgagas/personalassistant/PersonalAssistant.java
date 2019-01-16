package com.volgagas.personalassistant;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.volgagas.personalassistant.data.datasource.BaseApiService;
import com.volgagas.personalassistant.data.datasource.SPApiService;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.manager.TaskManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PersonalAssistant extends Application  {

    private PersonalAssistant application;

    private static BaseApiService baseApiService;
    private static SPApiService spApiService;

    /* Times
     * */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'", Locale.getDefault());
    private static SimpleDateFormat patternFromServer = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        application = this;

        TaskManager.getInstance();
    }

    public PersonalAssistant getApplication() {
        return application;
    }

    public static BaseApiService getBaseApiService() {
        return baseApiService;
    }

    public static SPApiService getSpApiService() {
        return spApiService;
    }

    public static void provideDynamics365Auth(String token) {
        OkHttpClient.Builder builderWithAuth = initBuilderAuth(token, "D365");
        OkHttpClient client = builderWithAuth.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.DYNAMICS_365)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        baseApiService = retrofit.create(BaseApiService.class);
    }

    public static void provideSharePointAuth(String token) {
        OkHttpClient.Builder builderWithAuth = initBuilderAuth(token, "SP");
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
                Timber.d("INIT D365");
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
                Timber.d("INIT SP");
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

    public static String getNextDayDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String result = dateFormat.format(calendar.getTime());

        return result + "00:00:00Z";
    }

    public static String getLastDayDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String result = dateFormat.format(calendar.getTime());

        return result + "00:00:00Z";
    }

    public static SimpleDateFormat getPatternFromServer() {
        return patternFromServer;
    }

    public static String getCurrentDataFormat() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = dateFormat.format(Calendar.getInstance().getTime());
        return result + "00:00:00Z";
    }

}
