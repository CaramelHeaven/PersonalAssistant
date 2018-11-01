package com.volgagas.personalassistant.presentation.start;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.volgagas.personalassistant.presentation.start.presenter.StartPresenter;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;
import com.volgagas.personalassistant.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class StartActivity extends BaseActivity implements StartView {

    private AuthenticationContext authContext;

    @InjectPresenter
    StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        permissionToEnableNfc = true;

        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);
    }

    @Override
    protected void sendDataToServer(String data) {
        presenter.setDataCodekey(data);

        authContext.acquireToken(StartActivity.this, Constants.DYNAMICS_365_DEV, Constants.CLIENT,
                Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken());

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
                PersonalAssistant.provideSharePointAuth(result.getAccessToken());

                Timber.d("COMPLETED SP CALLBACK");
                Timber.d("ca: " + CacheUser.getUser().toString());
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    @Override
    public void requestD365Token() {

    }

    @Override
    public void resultMatchedWithEquipment() {
        Toast.makeText(this, "Вы приложили карточку оборудования", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commonError() {
        Toast.makeText(this, "Common error", Toast.LENGTH_SHORT).show();
    }
}