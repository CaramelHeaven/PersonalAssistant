package com.volgagas.personalassistant.presentation.base;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationException;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.BuildConfig;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.TwoPermissions;
import com.volgagas.personalassistant.utils.manager.BroadcastManager;
import com.volgagas.personalassistant.utils.notifications.FileUploadNotification;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:04, 22/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * GodActivity for handler token if it dead. And checking auto-update apk
 */
public abstract class BaseGodActivity extends MvpAppCompatActivity {

    private CompositeDisposable disposable;
    private AuthenticationContext authContext;
    private String d365UserCache;
    private String dynamicsCurrentHttp;
    private String sharePointCache;
    private BroadcastManager broadcastManager;
    private boolean registerBroadcastReceiver = false;
    private IntentFilter filter;

    private SharedPreferences sharedPreferences;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        sharedPreferences = this.getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

        d365UserCache = sharedPreferences.getString(Constants.SP_D365_USER_CACHE, "");
        sharePointCache = sharedPreferences.getString(Constants.SP_SHARE_POINT_USER_CACHE, "");
        dynamicsCurrentHttp = sharedPreferences.getString(Constants.SP_CURRENT_HTTP, "");

        if (dynamicsCurrentHttp.equals("")) {
            dynamicsCurrentHttp = Constants.DYNAMICS_365;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateToken();
        listenerForRefreshTokens();

        //If user entering to application we get current versions application from server and if
        // newest available we pass action-key-word to here for reflect notification about it
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.UPDATE_APK))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> createNotification()));

        //listenerForProgress
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals(Constants.APK_PROGRESS_80) || result.equals(Constants.APK_PROGRESS_100)
                        || result.equals(Constants.APK_PROGRESS_50) || result.equals(Constants.APK_PROGRESS_FAILED))
                .subscribe(result -> {
                    //I know that this is shit of shits, but deadline bro
                    if (result.contains("80")) {
                        runOnUiThread(() -> FileUploadNotification.shared().updateNotification("80"));
                    } else if (result.contains("50")) {
                        runOnUiThread(() -> FileUploadNotification.shared().updateNotification("50"));
                    } else if (result.contains("FAILED")) {
                        runOnUiThread(() -> {
                            Toast.makeText(BaseGodActivity.this,
                                    "Ошибка при скачивании файла", Toast.LENGTH_SHORT).show();
                            FileUploadNotification.shared().deleteNotification();
                        });
                    } else if (result.contains("100")) {
                        runOnUiThread(() -> FileUploadNotification.shared().updateNotification("100"));

                        runOnUiThread(() -> new Handler().postDelayed(() -> {
                            Toast.makeText(BaseGodActivity.this, "Скачивание завершено", Toast.LENGTH_SHORT).show();
                            openFile();
                        }, 1300));
                    }
                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (registerBroadcastReceiver) {
            broadcastManager = new BroadcastManager();

            filter = new IntentFilter();
            filter.addAction(Constants.ACTION_UPDATE_APK);
            filter.addAction(Constants.ACTION_NOT_UPDATE_APK);

            registerReceiver(broadcastManager, filter);
        }
    }

    @Override
    protected void onPause() {
        if (registerBroadcastReceiver && broadcastManager != null) {
            unregisterReceiver(broadcastManager);
            broadcastManager = null;
        }

        super.onPause();
    }

    @Override
    protected void onStop() {
        disposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        disposable.clear();
        try {
            if (authContext != null) {
                if (d365Callback != null) {
                    authContext.cancelAuthenticationActivity(d365Callback.hashCode());
                }

                if (spCallback != null) {
                    authContext.cancelAuthenticationActivity(spCallback.hashCode());
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        authContext = null;
        d365Callback = null;
        spCallback = null;

        super.onDestroy();
    }

    /**
     * Update token after each 10 minutes which user spent in the app.
     * Class handler UpdateTokenHandler
     */
    private void updateToken() {
        disposable.add(RxBus.getInstance().getUpdates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshTokens));
    }

    /**
     * Channel where will be put states from two tokens refreshed.
     */
    private void listenerForRefreshTokens() {
        disposable.add(CommonChannel.getInstance().getTwoPermissionsSubject()
                .subscribeOn(Schedulers.io())
                .filter(TwoPermissions::allValuesIsTrue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    //if handler update token delivered in here, we check it. We won't to send
                    // this constant to channel [passUpdatedToken]
                    Timber.d("updated string: " + TwoPermissions.getInstance().getUpdatedString());
                    Timber.d("cons update token silent: " + Constants.UPDATE_TOKEN_SILENT);
                    if (!(TwoPermissions.getInstance().getUpdatedString().equals(Constants.UPDATE_TOKEN_SILENT))) {
                        Timber.d("im here: ");
                        RxBus.getInstance().passUpdatedToken(TwoPermissions.getInstance().getUpdatedString());
                    }
                    Timber.d("completed refresh: " + result);
                    TwoPermissions.getInstance().resetValues();
                }));
    }

    /**
     * Base method for refresh tokens from global bus listener in here. First of all - refresh
     * dynamics 365 after - refresh SharePoint
     *
     * @param result - string which define where we send observer data for update request to server
     */
    private void refreshTokens(String result) {
        Timber.d("RESULT: " + result);
        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);

        TwoPermissions.getInstance().resetValues();
        TwoPermissions.getInstance().setUpdatedString(result);

        if (d365UserCache.equals("")) {
            authContext.acquireToken(BaseGodActivity.this, Constants.DYNAMICS_365, Constants.CLIENT,
                    Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
        } else {
            authContext.acquireTokenSilentAsync(dynamicsCurrentHttp, Constants.CLIENT, d365UserCache, d365Callback);
        }
    }

    /**
     * Callback for check if token refreshed successful or not. Dynamics 365
     */
    private AuthenticationCallback<AuthenticationResult> d365Callback = new AuthenticationCallback<AuthenticationResult>() {
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setUserCliendId(result.getClientId());
                CacheUser.getUser().setDynamics365Token(result.getAccessToken());

                //Refresh d365 retrofit object
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken(), "");

                TwoPermissions permissions = TwoPermissions.getInstance();
                permissions.setD365Token(true);
                CommonChannel.sendTwoPermissions(permissions);

                if (sharePointCache.equals("")) {
                    authContext.acquireToken(BaseGodActivity.this, Constants.GRAPH, Constants.CLIENT,
                            Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", spCallback);
                } else {
                    authContext.acquireTokenSilentAsync(Constants.GRAPH, Constants.CLIENT,
                            sharePointCache, spCallback);
                }

            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    /**
     * Callback for check if token refreshed successful or not. SharePoint
     */
    private AuthenticationCallback<AuthenticationResult> spCallback = new AuthenticationCallback<AuthenticationResult>() {
        @SuppressLint("CheckResult")
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setSharePointToken(result.getAccessToken());

                //Refresh SharePoint token
                PersonalAssistant.provideSharePointAuth(result.getAccessToken());

                TwoPermissions permissions = TwoPermissions.getInstance();
                permissions.setSharePointToken(true);

                CommonChannel.sendTwoPermissions(permissions);
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    protected void createNotification() {
        Intent cancelUpdateIntent = new Intent(Constants.ACTION_NOT_UPDATE_APK);
        Intent acceptUpdateIntent = new Intent(Constants.ACTION_UPDATE_APK);

        PendingIntent cancelPendingIntent =
                PendingIntent.getBroadcast(this, 0, cancelUpdateIntent, 0);
        PendingIntent acceptPendingIntent =
                PendingIntent.getBroadcast(this, 0, acceptUpdateIntent, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, Constants.APP_NOFITICATION)
                        .setSmallIcon(R.drawable.ic_volgagas)
                        .setContentTitle("Обновление приложения")
                        .setContentText("Доступна более новая версия приложения. Обновить?")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Доступна более новая версия приложения. Обновить?"))
                        .addAction(R.drawable.ic_box, "Закрыть", cancelPendingIntent)
                        .addAction(R.drawable.ic_box_unpacked, "Обновить", acceptPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = Constants.APP_NOFITICATION;
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel for update apk",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        Notification notification = builder.build();

        notificationManager.notify(Constants.APP_NOTIFICATION_UPDATE_APP, notification);

        //update broadcast receiver for enable buttons in notification
        onResume();
    }

    /**
     * Open downloaded file
     */
    private void openFile() {
        File toInstall = new File(getFilesDir(), Constants.APK_FILE_NAME);

        Timber.d("OPEN FILE");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", toInstall);

            final Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setData(contentUri)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);

            startActivityForResult(intent, 0);
        } else {
            //not testing this
            Uri apkUri = Uri.fromFile(toInstall);
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }

    public void setRegisterBroadcastReceiver(boolean registerBroadcastReceiver) {
        this.registerBroadcastReceiver = registerBroadcastReceiver;
    }
}
