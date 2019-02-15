package com.volgagas.personalassistant.presentation.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionListenerAdapter;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.BuildConfig;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.about_user.InfoFragment;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.home.HomeFragment;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;
import com.volgagas.personalassistant.presentation.projects.FragmentProjects;
import com.volgagas.personalassistant.presentation.settings.SettingsActivity;
import com.volgagas.personalassistant.presentation.start.StartActivity;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;
import com.volgagas.personalassistant.utils.shared_preferenses.UtilsSharedPresefenses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
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
    private File fileApk;

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
        constraintLayout = findViewById(R.id.constraintLayout);

        fileApk = new File(getFilesDir(), "apkApp.apk");
        //fileApk = new File(kek, );

        Timber.d("FILE APK: " + fileApk.getPath() + " and absolute: " + fileApk.getAbsolutePath());
        setPermissionToEnableNfc(false);

        setSupportActionBar(toolbar);

        //test
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        infoSet = new ConstraintSet();

        provideBackgroundUIData();

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        infoSet.clone(this, R.layout.activity_constraint_about);

        setSupportActionBar(toolbar);

        setBottomNavigation();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                .commit();

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
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
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

//                    if (!fragmente.getTag().equals("HOME")) {
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
//                                .commit();
//                    }

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
                    Toast.makeText(this, "RUN", Toast.LENGTH_SHORT).show();
                    File toInstall = new File(getFilesDir(), "apkApp.apk");
                    //File newFile = new File(toInstall, );


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                        Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", toInstall);
                        Timber.d("KEKUS: " + contentUri.getPath());
                        Timber.d("KEKUS: " + contentUri.toString());

                        Timber.d("KEKUS: " + toInstall.getAbsolutePath());
                        Timber.d("KEKUS: " + contentUri.toString());

                        final Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(contentUri)
                                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);

                        startActivityForResult(intent, 0);
                    } else {
                        Uri apkUri = Uri.fromFile(toInstall);
                        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                    }

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
                Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight() - 240);
                ivUserImage.setImageBitmap(croppedBmp);
            }
        }

        tvName.setText(CacheUser.getUser().getName());
        tvCategory.setText(CacheUser.getUser().getPosition());
    }

    @Override
    public void saveFileFromServer(ResponseBody body) {
        // todo change the file location/name according to your needs

        Timber.d("HERE IM");
        if (fileApk.exists()) {
            boolean delete = fileApk.delete();
            Timber.d("DELETE OR NOT? " + delete);
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] fileReader = new byte[4096];

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            inputStream = body.byteStream();

            outputStream = new FileOutputStream(fileApk);

            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;

                Timber.d("file download: " + fileSizeDownloaded + " of " + fileSize);
            }

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Timber.d("COMPLETED");
        Toast.makeText(this, "COMPLETED", Toast.LENGTH_SHORT).show();
    }
}
