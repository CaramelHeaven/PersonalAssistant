package com.volgagas.personalassistant;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.volgagas.personalassistant.data.datasource.D365ApiService;
import com.volgagas.personalassistant.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class PersonalAssistant extends Application {

    private PersonalAssistant application;

    private static D365ApiService d365ApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        application = this;
    }

    public static D365ApiService getD365ApiService() {
        return d365ApiService;
    }

    public static void provideDynamics365Auth(String token) {
        OkHttpClient.Builder builderWithAuth = initBuilderAuth(token);
        OkHttpClient client = builderWithAuth.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.DYNAMICS_365_DEV)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        d365ApiService = retrofit.create(D365ApiService.class);
    }

    private static OkHttpClient.Builder initBuilderAuth(String token) {
        OkHttpClient.Builder builderD365 = new OkHttpClient().newBuilder();
        builderD365.readTimeout(10, TimeUnit.SECONDS);
        builderD365.connectTimeout(7, TimeUnit.SECONDS);
        builderD365.addInterceptor(chain -> {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json;odata=verbose")
                    .build();
            return chain.proceed(request);
        });
        HttpLoggingInterceptor interceptorD365 = new HttpLoggingInterceptor();
        interceptorD365.setLevel(HttpLoggingInterceptor.Level.BODY);
        builderD365.addInterceptor(interceptorD365);
        return builderD365;
    }
}
