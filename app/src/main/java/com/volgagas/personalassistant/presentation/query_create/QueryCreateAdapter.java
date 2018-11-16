package com.volgagas.personalassistant.presentation.query_create;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.volgagas.personalassistant.presentation.fill_request.FillRequestFragment;
import com.volgagas.personalassistant.presentation.who_is_the_recipient.RecipientFragment;

/**
 * Created by CaramelHeaven on 15:48, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryCreateAdapter extends FragmentPagerAdapter {

    public QueryCreateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return FillRequestFragment.newInstance();
            case 1:
                return RecipientFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
