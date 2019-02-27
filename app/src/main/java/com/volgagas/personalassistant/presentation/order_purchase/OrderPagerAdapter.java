package com.volgagas.personalassistant.presentation.order_purchase;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.order_purchase_order.PurchaseOrderFragment;
import com.volgagas.personalassistant.presentation.order_purchase_requestion.PurchaseRequestionFragment;

/**
 * Created by CaramelHeaven on 16:19, 27/02/2019.
 */
public class OrderPagerAdapter extends FragmentPagerAdapter {

    private int count = 2;

    public OrderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return PurchaseRequestionFragment.newInstance();
            case 1:
                return PurchaseOrderFragment.newInstance();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Заказы от вас";
            case 1:
                return "Принятые заказы";
        }
        return "";
    }

    @Override
    public int getCount() {
        return count;
    }
}
