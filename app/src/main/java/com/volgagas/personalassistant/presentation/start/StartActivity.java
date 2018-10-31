package com.volgagas.personalassistant.presentation.start;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.microsoft.aad.adal.AuthenticationActivity;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationConstants;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.main.MainActivity;
import com.volgagas.personalassistant.presentation.start.presenter.StartView;
import com.volgagas.personalassistant.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class StartActivity extends BaseActivity implements StartView {

    private AuthenticationContext authContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);
//
//        authContext.acquireToken(StartActivity.this, Constants.GRAPH, Constants.CLIENT,
//                Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);

        startActivity(new Intent(StartActivity.this, MainActivity.class));

        //startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void sendDataToServer(String data) {
        //nothing
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
            Timber.d("ZLFKZL:FZK:FL");
            if (result.getAccessToken() != null) {

                PersonalAssistant.provideDynamics365Auth(result.getAccessToken());
                /*PersonalAssistant.getD365ApiService().getSharePointTest("https://volagas.sharepoint.com/doc/_api/web/lists(guid'AE8634FB-D697-4BB0-B1BF-CD8220CFBE15')/Items/")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(q -> {
                            Timber.d("another result: " + q);
                        }, throwable -> {
                            Timber.d("thro: " + throwable.getCause());
                            Timber.d("thro: " + throwable.getMessage());
                        });
*/
                PersonalAssistant.getD365ApiService().getTest("https://volgagas-devdevaos.sandbox.ax.dynamics.com/data/SOWithAC?&$filter=(AC_ActivityStartDateTime ge 2018-10-15T00:00:00Z and AC_ActivityStartDateTime le 2018-10-16T00:00:00Z) and (SO_ServiceStage eq 'Распредел' or SO_ServiceStage eq 'ВРаботе') and (AC_Worker eq 'Михайлова Евгения Андреевна')")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(q -> {
                            Timber.d("another result: " + q);
                        }, throwable -> {
                            Timber.d("thro: " + throwable.getCause());
                            Timber.d("thro: " + throwable.getMessage());
                        });

               /* authContext.acquireToken(StartActivity.this, Constants.GRAPH, Constants.CLIENT,
                        Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", spCallback);*/
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    /*private AuthenticationCallback<AuthenticationResult> spCallback = new AuthenticationCallback<AuthenticationResult>() {
        @SuppressLint("CheckResult")
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                PersonalAssistant.provideSharePointAuth(result.getAccessToken());
                PersonalAssistant.getSpApiService().getTest("https://graph.microsoft.com/v1.0/sites/volagas.sharepoint.com,9a51e995-62f9-4b40-81c2-d167c4c79182,8603ccc9-1f11-4573-8fa2-140ef4204a1d/lists/ed91d81b-2c69-487d-a7eb-f924771488fb/items")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(q -> {
                            Timber.d("result: " + q);
                        }, throwable -> {
                            Timber.d("thro: " + throwable.getCause());
                            Timber.d("thro: " + throwable.getMessage());
                        });

            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };*/
}
