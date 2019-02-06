package com.volgagas.personalassistant.presentation.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationException;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.TwoPermissions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:04, 22/01/2019.
 */
public abstract class BaseGodActivity extends MvpAppCompatActivity {

    private CompositeDisposable disposable;
    private AuthenticationContext authContext;
    private String d365UserCache;
    private String dynamicsCurrentHttp;
    private String sharePointCache;

    private SharedPreferences sharedPreferences;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        Timber.d("ON STOP");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
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
        Timber.d("UPDATED TOKEN INITIAL: " + this.getClass().getSimpleName());
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
                    RxBus.getInstance().passUpdatedToken(TwoPermissions.getInstance().getUpdatedString());

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
        Timber.d("REFRESH PRESENTER: " + result);
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
                Timber.d("CALLED CHECK");
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

}
