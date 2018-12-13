package com.volgagas.personalassistant.presentation.start;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.main.MainActivity;
import com.volgagas.personalassistant.presentation.messenger.MessengerActivity;
import com.volgagas.personalassistant.presentation.start.presenter.StartPresenter;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;
import com.volgagas.personalassistant.utils.services.UpdateTokensService;

import timber.log.Timber;

public class StartActivity extends BaseActivity implements StartView {

    private AuthenticationContext authContext;

    @InjectPresenter
    StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setPermissionToEnableNfc(true);

        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);

        //xstartActivity(new Intent(this, CameraActivity.class));

        UpdateTokensService.getInstance();
        UpdateTokensService.startTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /*
     *  Save current data from card and send it to server in the D365 callback
     *  @param data - user codekey from mifare card
     */
    @Override
    protected void sendDataToServer(String data) {
        if (data != null && data.length() == 18) {
            presenter.setDataCodekey(data);

            //repeat auth
            if (ThreePermissions.getInstance().anyValueIsTrue()) {
                ThreePermissions.getInstance().resetValues();
            }
            CacheUser.getUser().clear();

            authContext.acquireToken(StartActivity.this, Constants.DYNAMICS_365, Constants.CLIENT,
                    Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
        } else {
            Toast.makeText(this, "Приложите карту еще раз", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void goToMainMenu() {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
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
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setUserCliendId(result.getClientId());
                CacheUser.getUser().setDynamics365Token(result.getAccessToken());

                if (PersonalAssistant.getBaseApiService() == null) {
                    Timber.d("INIT D365");
                    PersonalAssistant.provideDynamics365Auth(result.getAccessToken());
                }

                ThreePermissions permissions = ThreePermissions.getInstance();
                permissions.setD365Token(true);
                CommonChannel.sendPermissions(permissions);

                presenter.getUserData(presenter.getDataCodekey());

                authContext.acquireToken(StartActivity.this, Constants.GRAPH, Constants.CLIENT,
                        Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", spCallback);
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    private AuthenticationCallback<AuthenticationResult> spCallback = new AuthenticationCallback<AuthenticationResult>() {
        @SuppressLint("CheckResult")
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setSharePointToken(result.getAccessToken());

                if (PersonalAssistant.getSpApiService() == null) {
                    Timber.d("INIT SHARE POINT");
                    PersonalAssistant.provideSharePointAuth(result.getAccessToken());
                }

                ThreePermissions permissions = ThreePermissions.getInstance();
                permissions.setSharePointToken(true);
                CommonChannel.sendPermissions(permissions);
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    @Override
    public void resultMatchedWithEquipment() {
        Toast.makeText(this, "Вы приложили карточку оборудования", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commonError() {
        Toast.makeText(this, "Common error", Toast.LENGTH_SHORT).show();
    }
}