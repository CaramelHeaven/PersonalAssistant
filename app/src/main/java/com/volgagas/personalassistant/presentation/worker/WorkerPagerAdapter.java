package com.volgagas.personalassistant.presentation.worker;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.worker_history.WorkerHistoryFragment;
import com.volgagas.personalassistant.presentation.worker_today.WorkerTodayFragment;
import com.volgagas.personalassistant.presentation.worker_today_new.WorkerTodayNewFragment;

public class WorkerPagerAdapter extends FragmentPagerAdapter {

    public WorkerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return WorkerTodayFragment.newInstance();
            case 1:
                return WorkerHistoryFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "На сегодня";
            case 1:
                return "История";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
