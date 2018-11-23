package com.volgagas.personalassistant.presentation.kiosk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.kiosk.presenter.KioskPresenter;
import com.volgagas.personalassistant.presentation.kiosk.presenter.KioskView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;

import timber.log.Timber;

public class KioskActivity extends MvpAppCompatActivity implements KioskView {

    private Toolbar toolbar;
    private EditText etSearchTasks;
    private ViewPager vpContainer;
    private TabLayout tlContainer;

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

        provideEtSearch();
        provideTabLayoutAndViewPager();
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
}
