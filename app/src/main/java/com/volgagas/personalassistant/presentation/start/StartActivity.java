package com.volgagas.personalassistant.presentation.start;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
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
import com.volgagas.personalassistant.utils.UtilsDateTimeProvider;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import io.fabric.sdk.android.Fabric;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class StartActivity extends MvpAppCompatActivity implements StartView {

    private ProgressBar progressBar;

    private AuthenticationContext authContext;
    private SharedPreferences sharedPreferences;
    private String d365Cache, sharePointCache, dynamicsCurrentHttp;
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private IntentFilter[] intentFiltersArray;
    private String[][] techListArray;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private StringBuilder secretNumbers = null;
    private boolean permissionToEnableNfc = false;

    @InjectPresenter
    StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        progressBar = findViewById(R.id.progressBar);

        activateNFC();

        //unit current app version
        Constants.APP_CURRENT_VERSION = getResources().getString(R.string.currentVersionApp);

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

        //set DEV branch
        if (dynamicsCurrentHttp != null && dynamicsCurrentHttp.equals("")) {
            dynamicsCurrentHttp = Constants.DYNAMICS_365;

            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(Constants.SP_CURRENT_HTTP, dynamicsCurrentHttp);
            edit.apply();
        } else {
            Constants.DYNAMICS_365 = dynamicsCurrentHttp;
        }

        Timber.d("dynamics SHOW: " + dynamicsCurrentHttp);
        //todo remove it in dev release
        dynamicsCurrentHttp = Constants.DYNAMICS_TEST;
        Constants.DYNAMICS_365 = Constants.DYNAMICS_TEST;

        if (d365Cache.equals("")) {
            authContext.acquireToken(StartActivity.this, dynamicsCurrentHttp, Constants.CLIENT,
                    Constants.REDIRECT_URL, "", PromptBehavior.Auto, "", d365Callback);
        } else {
            Timber.d("REQUEST");
            authContext.acquireTokenSilentAsync(dynamicsCurrentHttp, Constants.CLIENT, d365Cache, d365Callback);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume: " + this.getClass().getSimpleName());
        Timber.d("get simplest name current class: " + this.getClass().getSimpleName());
        Timber.d("nfc adapter check: " + nfcAdapter.isEnabled() + " permission: " + permissionToEnableNfc);
        if (nfcAdapter != null && permissionToEnableNfc && nfcAdapter.isEnabled()) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListArray);
        }
    }

    @Override
    protected void onPause() {
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Timber.d("DISABLE THiS SHIT");
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    /**
     * Scanning NFC card and get first block.
     *
     * @param intent - intent for handler event.
     */
    @SuppressLint("CheckResult")
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Single.just(tag)
                .subscribeOn(Schedulers.computation())
                .map(MifareClassic::get)
                .doOnSuccess(mifareClassic -> {
                    boolean isAuthenticated = false;
                    try {
                        mifareClassic.connect();
                        if (mifareClassic.isConnected()) {
                            if (mifareClassic.authenticateSectorWithKeyA(0, MifareClassic.KEY_DEFAULT)) {
                                isAuthenticated = true;
                            }
                            if (isAuthenticated) {
                                int blockIndex = mifareClassic.sectorToBlock(0);
                                byte[] block = mifareClassic.readBlock(blockIndex);
                                secretNumbers = bytesToHexString(block);
                            }
                        }
                        mifareClassic.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (secretNumbers != null) {
                        sendDataToServer(secretNumbers.toString());
                    } else {
                        Toast.makeText(this, "Приложите карту еще раз", Toast.LENGTH_SHORT).show();
                    }
                }, this::unsuccessfulResult);
    }

    private void unsuccessfulResult(Throwable throwable) {
        Crashlytics.logException(throwable);
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
    protected void sendDataToServer(String data) {
        showProgress();

        //setPermissionToEnableNfc(false);
        //handlerNFC();

        if (data != null && data.length() == 18) {
            presenter.getUserData(data);
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
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(this, "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainMenu() {
        Fabric.with(this, new Crashlytics());

        Crashlytics.setUserIdentifier(CacheUser.getUser().getName());
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }

    @Override
    public void showErrorToEnter() {
        //setPermissionToEnableNfc(true);
        //handlerNFC();

        RxBus.getInstance().passDataToCommonChannel("TITLE_SHOW");
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Произошла ошибка при входе. Повторите еще раз", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableNFC() {
        permissionToEnableNfc = true;
        onResume();
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
            }

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

        //setPermissionToEnableNfc(true);
        //handlerNFC();

        Toast.makeText(this, "Вы приложили карточку оборудования", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commonError(Throwable throwable) {
        RxBus.getInstance().passDataToCommonChannel("TITLE_SHOW");

        //setPermissionToEnableNfc(true);
        //handlerNFC();

        Toasty.info(this, "Непредвиденная ошибка: " + throwable.getMessage()).show();
    }

    private void activateNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            pendingIntent = PendingIntent
                    .getActivity(this, 0,
                            new Intent(this, this.getClass())
                                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            try {
                filter.addDataType("*/*");
            } catch (IntentFilter.MalformedMimeTypeException e) {
                throw new RuntimeException("Exception", e);
            }
            intentFiltersArray = new IntentFilter[]{filter,};
            techListArray = new String[][]{
                    {MifareClassic.class.getName()}
            };
        } else {
            Toast.makeText(this, "NFC is not supported on your device", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Convert byte to string. Added 0x20 in the start position and 000000 to the end position
     * for server.
     *
     * @param bytes - bytes from onNewIntent scanned first block from MifareClassic card
     */
    private StringBuilder bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder builder = new StringBuilder(new String(hexChars));

        return specialConvert(new StringBuilder(builder
                .substring(0, 8))).insert(0, "0x20")
                .append("000000");
    }

    /**
     * Change string position. When we scanned card we got numbers for example A59121 but we need
     * reverse positions it.
     *
     * @param stringFromBlock - string firstly scanned block
     */
    private StringBuilder specialConvert(StringBuilder stringFromBlock) {
        if (stringFromBlock.length() == 8) {
            stringFromBlock = new StringBuilder(stringFromBlock).reverse();
            char[] array = stringFromBlock.toString().toCharArray();
            for (int i = 0; i < array.length - 1; i = i + 2) {
                char tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
            }
            return stringFromBlock.delete(0, stringFromBlock.length()).append(array);
        }
        return null;
    }
}