package com.volgagas.personalassistant.presentation.projects;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.contracts.ContractFragment;
import com.volgagas.personalassistant.presentation.queries.QueryFragment;

public class PagerProjectsAdapter extends FragmentPagerAdapter {

    private int count = 2;

    public PagerProjectsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return QueryFragment.newInstance();
            case 1:
                return ContractFragment.newInstance();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Заявки";
            case 1:
                return "Договоры";
        }
        return "";
    }

    @Override
    public int getCount() {
        return count;
    }
}
