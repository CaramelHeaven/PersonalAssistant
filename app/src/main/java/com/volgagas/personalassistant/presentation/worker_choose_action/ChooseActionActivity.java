package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.base.BaseGodActivity;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityPresenter;
import com.volgagas.personalassistant.presentation.worker_choose_action.presenter_activity.ChooseActivityView;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.TwoPermissions;

import timber.log.Timber;

public class ChooseActionActivity extends BaseActivity implements ChooseActivityView {

    private AuthenticationContext authContext;
    private String d365UserCache;
    private String dynamicsCurrentHttp;
    private String action;

    private SharedPreferences sharedPreferences;


    @InjectPresenter
    ChooseActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);
        setPermissionToEnableNfc(false);

        action = getIntent().getStringExtra("ACTION");

        sharedPreferences = this.getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

        d365UserCache = sharedPreferences.getString(Constants.SP_D365_USER_CACHE, "");
        dynamicsCurrentHttp = sharedPreferences.getString(Constants.SP_CURRENT_HTTP, "");

        if (dynamicsCurrentHttp.equals("")) {
            dynamicsCurrentHttp = Constants.DYNAMICS_365;
        }

        switch (action) {
            case "ADD_MORE_NOMENCLATURES":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                NomenclatureFragment.newInstance(getIntent().getParcelableExtra("TASK"),
                                        Constants.ADD_MORE_NOMENCLATURES))
                        .commit();
                break;
            case "USUAL":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ChooseActionFragment
                                .newInstance(getIntent().getParcelableExtra("TASK")))
                        .commit();
                //startActivity(new Intent(ChooseActionActivity.this, NomenclatureBarcodeActivity.class));
                break;
        }

        authContext = new AuthenticationContext(this, Constants.AUTH_URL, true);

        authContext.acquireTokenSilentAsync(dynamicsCurrentHttp, Constants.CLIENT, d365UserCache, d365Callback);
    }

    @Override
    protected void sendDataToServer(String data) {
        RxBus.getInstance().passScanData(data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void enableNFC() {

    }

    @Override
    public void initialBasePresenter() {
        setBasePresenter(presenter);
    }

    private AuthenticationCallback<AuthenticationResult> d365Callback = new AuthenticationCallback<AuthenticationResult>() {
        @Override
        public void onSuccess(AuthenticationResult result) {
            if (result.getAccessToken() != null) {
                Timber.d("CALLED CHECK");
                CacheUser.getUser().setUserCliendId(result.getClientId());
                CacheUser.getUser().setDynamics365Token(result.getAccessToken());

                //Refresh d365 retrofit object
                PersonalAssistant.provideDynamics365Auth(result.getAccessToken(), "");

                RxBus.getInstance().passDataToCommonChannel("PERMISSION_TO_LOAD_NOMENCLATURES");
            }
        }

        @Override
        public void onError(Exception exc) {

        }
    };
}
