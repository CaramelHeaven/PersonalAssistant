package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.order_new_additionally.OrderNewAdditionallyFragment;
import com.volgagas.personalassistant.presentation.order_new_base.OrderNewBaseFragment;

/**
 * Created by CaramelHeaven on 12:41, 25/12/2018.
 */
public class OrderViewPager extends FragmentPagerAdapter {

    public OrderViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return OrderNewBaseFragment.newInstance();
            case 1:
                return OrderNewAdditionallyFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Основное";
            case 1:
                return "Дополнительно";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
