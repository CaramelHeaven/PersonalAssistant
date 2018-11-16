package com.volgagas.personalassistant.presentation.projects;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.projects.contracts.ContractFragment;
import com.volgagas.personalassistant.presentation.projects.queries.QueryFragment;
import com.volgagas.personalassistant.presentation.projects.work.WorkFragment;

public class PagerProjectsAdapter extends FragmentPagerAdapter {

    private int count = 3;

    public PagerProjectsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return WorkFragment.newInstance();
            case 1:
                return QueryFragment.newInstance();
            case 2:
                return ContractFragment.newInstance();
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
            case 2:
                return "Договоры";
        }
        return "";
    }

    @Override
    public int getCount() {
        return count;
    }
}
