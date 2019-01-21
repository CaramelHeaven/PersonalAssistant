package com.volgagas.personalassistant.presentation.kiosk;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.kiosk.presenter.KioskPresenter;
import com.volgagas.personalassistant.presentation.kiosk.presenter.KioskView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;
import com.volgagas.personalassistant.utils.bus.models.AddedTask;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class KioskActivity extends BaseActivity implements KioskView {

    private Toolbar toolbar;
    private EditText etSearchTasks;
    private ViewPager vpContainer;
    private TabLayout tlContainer;
    private Button btnSend;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    private KioskPagerAdapter pagerAdapter;

    @InjectPresenter
    KioskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);
        toolbar = findViewById(R.id.toolbar);
        etSearchTasks = findViewById(R.id.et_search);
        vpContainer = findViewById(R.id.vp_container);
        tlContainer = findViewById(R.id.tl_container);
        btnSend = findViewById(R.id.btn_send);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        provideEtSearch();
        provideTabLayoutAndViewPager();

        btnSend.setOnClickListener(v -> {
            if (presenter.getSenderTasks().size() > 0) {
                buildAlertDialog();
                setPermissionToEnableNfc(true);
                handlerNFC();

                Timber.d("check permission: " + isPermissionToEnableNfc());
            } else {
                Toast.makeText(KioskActivity.this, "Задачи не выбраны", Toast.LENGTH_SHORT).show();
            }
        });

        Timber.d("I'm IN KEK: " + CacheUser.getUser().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Listener for add task to presenter from KioskTaskFragment
     *
     * @param data - container which contains simple data class Task
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void addedTaskToPresenter(AddedTask data) {
        presenter.addTask(data.getTask());
    }

    /**
     * Remove task
     *
     * @param taskTemplate - task for remove from presenter
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void removeTaskFromPresenter(TaskTemplate taskTemplate) {
        presenter.removeTask(taskTemplate);
    }

    @Override
    protected void sendDataToServer(String data) {
        if (CacheUser.getUser().getCodekeyList().contains(data.substring(2, data.length()))) {
            presenter.sendData();
        } else {
            Toasty.error(this, "Приложена не ваша карта").show();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideTabLayoutAndViewPager() {
        pagerAdapter = new KioskPagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);
        tlContainer.setupWithViewPager(vpContainer);

        vpContainer.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                btnSend.setAlpha(positionOffset);

                if (position == 1) {
                    btnSend.setAlpha(1);
                    btnSend.setEnabled(true);
                } else {
                    btnSend.setEnabled(false);
                }
            }
        });
    }

    private void provideEtSearch() {
        etSearchTasks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //send entered letters to KioskTaskFragment
                Timber.d("char sequence: " + s);
                GlobalBus.getEventBus().post(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void buildAlertDialog() {
        alertDialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setTitle("Сканирование")
                .setMessage("Приложите карту для подтверждения задач")
                .setCancelable(true)
                .setOnCancelListener(dialog -> {
                    Timber.d("set on cancel listener");
                    setPermissionToEnableNfc(false);
                    handlerNFC();
                });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void sendTemplatesProgress() {
        if (alertDialog != null) {
            alertDialog.hide();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Отправляем шаблоны");
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void completedKiosk() {
        alertDialog.hide();
        progressDialog.hide();

        alertDialog = null;
        progressDialog = null;
        Toasty.success(KioskActivity.this, "Шаблоны созданы!", Toast.LENGTH_SHORT, true).show();

        finish();
    }

    @Override
    public void handlerError() {

    }

    @Override
    public void errorFromCreatedTask(String error) {
        Toasty.error(this, error).show();
    }
}
