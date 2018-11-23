package com.volgagas.personalassistant.presentation.kiosk;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.kiosk_added_tasks.KioskAddedTaskFragment;
import com.volgagas.personalassistant.presentation.kiosk_tasks.KioskTaskFragment;

/**
 * Created by CaramelHeaven on 17:15, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class KioskPagerAdapter extends FragmentPagerAdapter {

    public KioskPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return KioskTaskFragment.newInstance();
            case 1:
                return KioskAddedTaskFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Шаблонные задачи";
            case 1:
                return "Добавленные";
            default:
                return "";
        }
    }
}
