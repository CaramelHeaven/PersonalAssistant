package com.volgagas.personalassistant.presentation.projects;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.contracts.ContractFragment;
import com.volgagas.personalassistant.presentation.queries.QueryFragment;
import com.volgagas.personalassistant.presentation.query_from_user.QueryFromUserFragment;
import com.volgagas.personalassistant.presentation.query_from_user.presenter.QueryFromUserView;
import com.volgagas.personalassistant.presentation.query_to_user.QueryToUserFragment;

public class PagerProjectsAdapter extends FragmentPagerAdapter {

    private int count = 3;

    public PagerProjectsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return QueryFromUserFragment.newInstance();
            case 1:
                return QueryToUserFragment.newInstance();
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
                return "Заявки (от вас)";
            case 1:
                return "Заявки (к вам)";
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
