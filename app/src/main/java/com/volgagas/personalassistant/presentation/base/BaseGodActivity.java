package com.volgagas.personalassistant.presentation.base;

import android.annotation.SuppressLint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateToken();
        listenerForRefreshTokens();
    }

    @Override
    protected void onStop() {
        disposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("ON DESTROY GOD ACTIVITY");
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
        disposable.add(RxBus.getInstance().getUpdates().subscribe(result -> {
            Timber.d("REFRESHING MY TOKENS: " + result);
            refreshTokens(result);
        }));
    }

    /**
     * Channel where will be put states from two tokens refreshed.
     */
    private void listenerForRefreshTokens() {
        disposable.add(CommonChannel.getInstance().getTwoPermissionsSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.allValuesIsTrue()) {
                        Timber.d("COMPLETED LALALA");
                        RxBus.getInstance().passUpdatedToken(TwoPermissions.getInstance().getUpdatedString());

                        TwoPermissions.getInstance().resetValues();
                    }
                }));
    }

    /**
     * Base method for refresh tokens from global bus listener in here. First of all - refresh
     * dynamics 365 after - refresh SharePoint
     */
    private void refreshTokens(String result) {
        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);

        TwoPermissions.getInstance().resetValues();
        TwoPermissions.getInstance().setUpdatedString(result);

        authContext.acquireToken(BaseGodActivity.this, Constants.DYNAMICS_365, Constants.CLIENT,
                Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
    }

    /**
     * Callback for check if token refreshed successful or not. Dynamics 365
     */
    private AuthenticationCallback<AuthenticationResult> d365Callback = new AuthenticationCallback<AuthenticationResult>() {
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setUserCliendId(result.getClientId());
                Timber.d("DIFFERENCE BETWEEN: " + result.getAccessToken());
                CacheUser.getUser().setDynamics365Token(result.getAccessToken());

                //Refresh d365 retrofit object
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken(), "");

                TwoPermissions permissions = TwoPermissions.getInstance();
                permissions.setD365Token(true);
                CommonChannel.sendTwoPermissions(permissions);

                authContext.acquireToken(BaseGodActivity.this, Constants.GRAPH, Constants.CLIENT,
                        Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", spCallback);
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
