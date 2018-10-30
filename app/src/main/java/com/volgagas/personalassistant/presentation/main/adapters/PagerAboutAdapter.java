package com.volgagas.personalassistant.presentation.main.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.about_user.dashboard.DashboardFragment;
import com.volgagas.personalassistant.presentation.about_user.info.InfoFragment;

public class PagerAboutAdapter extends FragmentPagerAdapter {

    int count = 2;

    public PagerAboutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return InfoFragment.newInstance();
            case 1:
                return DashboardFragment.newInstance();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Информация";
            case 1:
                return "Dashboard";
        }
        return "";
    }

    @Override
    public int getCount() {
        return count;
    }
}
