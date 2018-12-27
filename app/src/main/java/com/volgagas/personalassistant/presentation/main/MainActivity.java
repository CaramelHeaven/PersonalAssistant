package com.volgagas.personalassistant.presentation.main;

import android.annotation.SuppressLint;
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
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.presentation.about_user.InfoFragment;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.home.HomeFragment;
import com.volgagas.personalassistant.presentation.main.presenter.MainPresenter;
import com.volgagas.personalassistant.presentation.main.presenter.MainView;
import com.volgagas.personalassistant.presentation.projects.FragmentProjects;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;

import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

    private BottomNavigationView bnvNavigation;
    private Toolbar toolbar;
    private CircleImageView ivUserImage;
    private ConstraintLayout constraintLayout;
    private TextView tvName, tvCategory, tvTitleProblem;

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
                .subscribe(result -> {
                    Timber.d("result: " + result);
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    // requestFullSet.applyTo(constraintLayout);
                }, throwable -> {
                    Timber.d("tho: " + throwable.getCause());
                    Timber.d("tho: " + throwable.getMessage());
                });

        //provideMessanger();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onResume() {
        super.onResume();

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

                    break;
                case R.id.action_info:
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

                    break;
            }
            return true;
        });
    }
}
