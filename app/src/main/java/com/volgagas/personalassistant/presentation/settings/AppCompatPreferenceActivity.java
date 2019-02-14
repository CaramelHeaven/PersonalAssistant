package com.volgagas.personalassistant.presentation.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * Preferences abstract
 */
public abstract class AppCompatPreferenceActivity extends PreferenceActivity {

    private AppCompatDelegate mDelegate;
    private CompositeDisposable disposable;
    private AuthenticationContext authenticationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);

        disposable = new CompositeDisposable();

        disposable.add(RxBus.getInstance().getUpdates()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.contains(Constants.DYNAMICS_PROD) || result.contains(Constants.DYNAMICS_TEST)) {
                        refreshTokens(result);
                    }
                }));
    }

    private void refreshTokens(String resultIrl) {
        authenticationContext = new AuthenticationContext(this, Constants.AUTH_URL, true);

        authenticationContext.acquireToken(AppCompatPreferenceActivity.this, resultIrl, Constants.CLIENT,
                Constants.REDIRECT_URL, "", PromptBehavior.Auto, "",
                d365CallbackWithNewUrl);
    }

    private AuthenticationCallback<AuthenticationResult> d365CallbackWithNewUrl = new AuthenticationCallback<AuthenticationResult>() {
        @SuppressLint("CheckResult")
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                CacheUser.getUser().setUserCliendId(result.getClientId());
                CacheUser.getUser().setDynamics365Token(result.getAccessToken());

                SharedPreferences sharedPreferences = getApplicationContext()
                        .getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

                //Refresh d365 retrofit object
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken(),
                        sharedPreferences.getString(Constants.SP_CURRENT_HTTP, ""));

                Toasty.info(getApplicationContext(), "Ссылка обновлена").show();
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        authenticationContext = null;
        super.onDestroy();
        getDelegate().onDestroy();
    }

    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
