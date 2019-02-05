package com.volgagas.personalassistant.presentation.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.crashlytics.android.Crashlytics;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.main.MainActivity;
import com.volgagas.personalassistant.presentation.start.presenter.StartPresenter;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;
import com.volgagas.personalassistant.presentation.start_login_card.StartLoginCardFragment;
import com.volgagas.personalassistant.presentation.start_splash.StartSplashFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;

import es.dmoral.toasty.Toasty;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class StartActivity extends BaseActivity implements StartView {

    private ProgressBar progressBar;

    private AuthenticationContext authContext;
    private SharedPreferences sharedPreferences;
    private String d365Cache, sharePointCache, dynamicsCurrentHttp;

    @InjectPresenter
    StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        progressBar = findViewById(R.id.progressBar);

        setPermissionToEnableNfc(false);

        //clear permissions
        ThreePermissions permissions = ThreePermissions.getInstance();
        permissions.resetValues();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, StartSplashFragment.newInstance())
                .commit();

        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);
        sharedPreferences = this.getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

        d365Cache = sharedPreferences.getString(Constants.SP_D365_USER_CACHE, "");
        sharePointCache = sharedPreferences.getString(Constants.SP_SHARE_POINT_USER_CACHE, "");
        dynamicsCurrentHttp = sharedPreferences.getString(Constants.SP_CURRENT_HTTP, "");

        if (dynamicsCurrentHttp.equals("")) {
            dynamicsCurrentHttp = Constants.DYNAMICS_365;
        }

        if (d365Cache.equals("")) {
            Timber.d("SIMPLE");
            authContext.acquireToken(StartActivity.this, dynamicsCurrentHttp, Constants.CLIENT,
                    Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
        } else {
            Timber.d("ASYNS");
            authContext.acquireTokenSilentAsync(dynamicsCurrentHttp, Constants.CLIENT, d365Cache, d365Callback);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Save current data from card and send it to server in the D365 callback
     *
     * @param data - user codekey from mifare card
     */
    @Override
    protected void sendDataToServer(String data) {
        showProgress();

        setPermissionToEnableNfc(false);
        handlerNFC();

        if (data != null && data.length() == 18) {
            presenter.getUserData(data);
            //repeat auth
//            if (ThreePermissions.getInstance().anyValueIsTrue()) {
//                ThreePermissions.getInstance().resetValues();
//            }
//            CacheUser.getUser().clear();
        } else {
            Toast.makeText(this, "Приложите карту еще раз", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        RxBus.getInstance().passDataToCommonChannel("TITLE_HIDE");
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToMainMenu() {
        Fabric.with(this, new Crashlytics());

        Crashlytics.setUserIdentifier(CacheUser.getUser().getName());
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }

    @Override
    public void showErrorToEnter() {
        setPermissionToEnableNfc(true);
        handlerNFC();

        RxBus.getInstance().passDataToCommonChannel("TITLE_SHOW");
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Произошла ошибка при входе. Повторите еще раз", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableNFC() {
        Timber.d("PERMISSION");
        setPermissionToEnableNfc(true);
        handlerNFC();

        sendDataToServer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (authContext != null) {
            authContext.onActivityResult(requestCode, resultCode, data);
        }
    }

    private AuthenticationCallback<AuthenticationResult> d365Callback = new AuthenticationCallback<AuthenticationResult>() {
        @SuppressLint("CheckResult")
        @Override
        public void onSuccess(AuthenticationResult result) {
            //d365 cache
            if (d365Cache.equals("")) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                d365Cache = result.getUserInfo().getUserId();

                editor.putString(Constants.SP_D365_USER_CACHE, d365Cache);
                editor.apply();
            }

            //Init first D365 interface retrofit network and send to observer data success API
            if (PersonalAssistant.getBaseApiService() == null) {
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken(), "");
            }

            ThreePermissions permissions = ThreePermissions.getInstance();
            permissions.setD365Token(true);
            CommonChannel.sendPermissions(permissions);

            if (sharePointCache.equals("")) {
                authContext.acquireToken(StartActivity.this, Constants.GRAPH, Constants.CLIENT,
                        Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", spCallback);
            } else {
                authContext.acquireTokenSilentAsync(Constants.GRAPH, Constants.CLIENT,
                        sharePointCache, spCallback);
            }

        }

        @Override
        public void onError(Exception exc) {

        }
    };

    private AuthenticationCallback<AuthenticationResult> spCallback = new AuthenticationCallback<AuthenticationResult>() {
        @Override
        public void onSuccess(AuthenticationResult result) {
            //share point cache
            if (sharePointCache.equals("")) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                sharePointCache = result.getUserInfo().getUserId();

                editor.putString(Constants.SP_SHARE_POINT_USER_CACHE, sharePointCache);
                editor.apply();

                Timber.d("FUCK U: " + sharedPreferences.getString(Constants.SP_SHARE_POINT_USER_CACHE, ""));
            }

            Timber.d("SHARE POINT");

            //init network share point api
            if (PersonalAssistant.getSpApiService() == null) {
                PersonalAssistant.provideSharePointAuth(result.getAccessToken());
            }

            ThreePermissions permissions = ThreePermissions.getInstance();
            permissions.setSharePointToken(true);
            CommonChannel.sendPermissions(permissions);

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit)
                    .replace(R.id.fragment_container, StartLoginCardFragment.newInstance())
                    .commit();
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    @Override
    public void resultMatchedWithEquipment() {
        RxBus.getInstance().passDataToCommonChannel("TITLE_SHOW");

        setPermissionToEnableNfc(true);
        handlerNFC();

        Toast.makeText(this, "Вы приложили карточку оборудования", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commonError() {
        RxBus.getInstance().passDataToCommonChannel("TITLE_SHOW");

        setPermissionToEnableNfc(true);
        handlerNFC();

        Toasty.info(this, "Приложите карту еще раз").show();
    }

    @Override
    public void initialBasePresenter() {
        //nothing
    }
}