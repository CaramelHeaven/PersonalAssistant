package com.volgagas.personalassistant.presentation.main;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionListenerAdapter;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.crashlytics.android.Crashlytics;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.about_user.InfoFragment;
import com.volgagas.personalassistant.presentation.about_user_certificates.CertificatesActivity;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.home.HomeFragment;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;
import com.volgagas.personalassistant.presentation.projects.FragmentProjects;
import com.volgagas.personalassistant.presentation.settings.SettingsActivity;
import com.volgagas.personalassistant.presentation.start.StartActivity;
import com.volgagas.personalassistant.presentation.worker_camera.PermissionsDelegate;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;
import com.volgagas.personalassistant.utils.notifications.FileUploadNotification;
import com.volgagas.personalassistant.utils.shared_preferenses.UtilsSharedPresefenses;

import java.io.File;
import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private CircleImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private TextView tvName, tvCategory;
    private ImageButton ibSettings, ibLogout;
    private RelativeLayout rlCertificates;

    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private boolean hasPermissions;

    private ConstraintSet homeSet, projectsSet, infoSet;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @InjectPresenter
    MainPresenter presenter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_home);
        ivUserImage = findViewById(R.id.imageView);
        bnvNavigation = findViewById(R.id.bnv_navigation);
        tvName = findViewById(R.id.tv_name);
        tvCategory = findViewById(R.id.tv_category);
        ibSettings = findViewById(R.id.iv_settings);
        ibLogout = findViewById(R.id.iv_logout);
        toolbar = findViewById(R.id.toolbar);
        rlCertificates = findViewById(R.id.rl_certificates);
        constraintLayout = findViewById(R.id.constraintLayout);

        setPermissionToEnableNfc(false);
        setSupportActionBar(toolbar);

        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        infoSet = new ConstraintSet();

        provideBackgroundUIData();
        //init release

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        infoSet.clone(this, R.layout.activity_constraint_about);

        setSupportActionBar(toolbar);

        setBottomNavigation();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                .commit();

        hasPermissions = permissionsDelegate.hasCameraPermission();

        if (hasPermissions) {
            presenter.acceptToCheckListOfApkes();
        } else {
            permissionsDelegate.requestPermissions();
        }

        PassDataChannel passDataChannel = PassDataChannel.getInstance();

        passDataChannel.getSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> TransitionManager.beginDelayedTransition(constraintLayout), throwable -> {
                    Timber.d("tho: " + throwable.getCause());
                    Timber.d("tho: " + throwable.getMessage());
                });

        ibSettings.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));

        ibLogout.setOnClickListener(v -> {
            CacheUser.clear();
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        });

        rlCertificates.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CertificatesActivity.class)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            hasPermissions = true;

            presenter.acceptToCheckListOfApkes();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(Constants.APP_NOTIFICATION_UPDATE_APP);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Fragment projectsTest = getSupportFragmentManager().findFragmentByTag("PROJECTS");
        if (projectsTest == null) {
            Fragment infoTest = getSupportFragmentManager().findFragmentByTag("INFO");
            if (infoTest != null) {
                TransitionManager.beginDelayedTransition(constraintLayout);
                homeSet.applyTo(constraintLayout);
            }
        } else {
            TransitionManager.beginDelayedTransition(constraintLayout);
            homeSet.applyTo(constraintLayout);
        }
    }


    @Override
    protected void sendDataToServer(String data) {

    }

    private void setBottomNavigation() {
        bnvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    homeSet.applyTo(constraintLayout);

                    break;
                case R.id.action_project:
                    if (!UtilsSharedPresefenses.getInstance().getPermissionCap(getApplicationContext())) {
                        Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
                    } else {
                        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag("PROJECTS");

                        AutoTransition autoTransition = new AutoTransition();

                        autoTransition.addListener(new TransitionListenerAdapter() {
                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                if (fragment2 == null) {
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragment_container_projects, FragmentProjects.newInstance(), "PROJECTS")
                                            .commit();
                                }
                            }
                        });

                        TransitionManager.beginDelayedTransition(constraintLayout, autoTransition);
                        projectsSet.applyTo(constraintLayout);
                    }

                    break;
                case R.id.action_info:
                    if (!UtilsSharedPresefenses.getInstance().getPermissionCap(getApplicationContext())) {
                        Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
                    } else {
                        Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("INFO");

                        AutoTransition autoTransition1 = new AutoTransition();

                        autoTransition1.addListener(new TransitionListenerAdapter() {
                            @Override
                            public void onTransitionEnd(@NonNull Transition transition) {
                                if (fragment1 == null) {
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragment_container_about, InfoFragment.newInstance(), "INFO")
                                            .commit();
                                }
                            }
                        });

                        TransitionManager.beginDelayedTransition(constraintLayout, autoTransition1);
                        infoSet.applyTo(constraintLayout);
                    }
                    break;
            }
            return true;
        });
    }

    private void provideBackgroundUIData() {
        if (CacheUser.getUser().getUserImage() != null) {
            Timber.d("cache != null");
            byte[] data = Base64.decode(CacheUser.getUser().getUserImage().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap != null) {
                if (bitmap.getHeight() - 240 > 0) {
                    Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight() - 240);
                    ivUserImage.setImageBitmap(croppedBmp);
                } else {
                    Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    ivUserImage.setImageBitmap(croppedBmp);
                }
            }
        }

        tvName.setText(CacheUser.getUser().getName());
        tvCategory.setText(CacheUser.getUser().getPosition());
    }

    @Override
    public void createProgressNotification() {
        //create context here
        FileUploadNotification.shared().setContext(getApplicationContext());
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        FileUploadNotification.shared().deleteNotification();
    }
}
