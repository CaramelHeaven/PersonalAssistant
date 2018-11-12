package com.volgagas.personalassistant.presentation.main;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.about_user.InfoFragment;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.home.HomeFragment;
import com.volgagas.personalassistant.presentation.main.adapters.PagerProjectsAdapter;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;

import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private RelativeLayout rlContainer;
    private CircleImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private ViewPager vpProjectsContainer;
    private TabLayout tabLayout;
    private FrameLayout fragmentContainer, fragmentTest;
    private TextView tvName, tvCategory;

    private PagerProjectsAdapter projectsAdapter;
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
        constraintLayout = findViewById(R.id.constraintLayout);
        vpProjectsContainer = findViewById(R.id.vp_container);
        tabLayout = findViewById(R.id.tabLayout);
        fragmentContainer = findViewById(R.id.fragment_container);
        fragmentTest = findViewById(R.id.fragment_container_about);
        homeSet = new ConstraintSet();
        projectsSet = new ConstraintSet();
        infoSet = new ConstraintSet();

        if (CacheUser.getUser().getUserImage() != null) {
            byte[] data = Base64.decode(CacheUser.getUser().getUserImage().getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight() - 240);
            ivUserImage.setImageBitmap(croppedBmp);
        }
        tvName.setText(CacheUser.getUser().getName());
        tvCategory.setText(CacheUser.getUser().getPosition());

        projectsAdapter = new PagerProjectsAdapter(getSupportFragmentManager());

        homeSet.clone(constraintLayout);
        projectsSet.clone(this, R.layout.activity_constraint_projects);
        infoSet.clone(this, R.layout.activity_constraint_about);

        setSupportActionBar(toolbar);

        setBottomNavigation();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                .commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        JsonObject objectBase = new JsonObject();
        JsonObject metadataObject = new JsonObject();
        metadataObject.add("type", new JsonPrimitive("SP.Data.List7ListItem"));

        JsonArray array = new JsonArray(1);
        array.add(13);

        JsonObject object = new JsonObject();
        JsonObject collectionData = new JsonObject();
        collectionData.add("type", new JsonPrimitive("Collection(Edm.Int32)"));
        object.add("__metadata", collectionData);
        object.add("results", array);

        objectBase.add("__metadata", metadataObject);
        objectBase.add("Title", new JsonPrimitive("TestFromMobile"));
        objectBase.add("CategoryLookup0Id", new JsonPrimitive(1));
        objectBase.add("Comment", new JsonPrimitive("description test"));
        objectBase.add("DueDate", new JsonPrimitive("2018-11-30"));
        objectBase.add("AssignedToId", object);
        objectBase.add("LastText", new JsonPrimitive("my last comment"));

//        PersonalAssistant.getSpApiService()
//                .sendTest("https://volagas.sharepoint.com/doc/_api/web/lists(guid'895e45dd-17ac-41bd-9a41-3d72bd0cbfc7')/items", objectBase)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                    Timber.d("result: " + result);
//                }, throwable -> {
//                    Timber.d("thrwable: " + throwable.getMessage());
//                    Timber.d("thrwable: " + throwable.getCause());
//                });
    }

    @Override
    protected void sendDataToServer(String data) {

    }

    private void setBottomNavigation() {
        bnvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    Fragment fragmente = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                    if (!fragmente.getTag().equals("HOME")) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, HomeFragment.newInstance(), "HOME")
                                .commit();
                    }
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    homeSet.applyTo(constraintLayout);
                    break;
                case R.id.action_project:
                    vpProjectsContainer.setAdapter(projectsAdapter);
                    tabLayout.setupWithViewPager(vpProjectsContainer);

                    openProjects();
                    break;
                case R.id.action_info:
                    Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("INFO");

                    if (fragment1 == null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container_about, InfoFragment.newInstance(), "INFO")
                                .commit();
                    }
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    infoSet.applyTo(constraintLayout);
                    break;
            }
            return true;
        });
    }

    private void openHome() {

    }

    private void openProjects() {
        TransitionManager.beginDelayedTransition(constraintLayout);
        projectsSet.applyTo(constraintLayout);
    }
}
