package com.volgagas.personalassistant.presentation.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.projects.queries.QueryFragment;
import com.volgagas.personalassistant.presentation.projects.work.WorkFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int count = 2;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return WorkFragment.newInstance();
            case 1:
                return QueryFragment.newInstance();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Работа";
            case 1:
                return "Заявки";
        }
        return "";
    }

    @Override
    public int getCount() {
        return count;
    }
}
