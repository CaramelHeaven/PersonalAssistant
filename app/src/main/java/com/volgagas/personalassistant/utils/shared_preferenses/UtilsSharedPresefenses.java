package com.volgagas.personalassistant.utils.shared_preferenses;

import android.content.Context;
import android.content.SharedPreferences;

import com.volgagas.personalassistant.utils.Constants;

/**
 * Created by CaramelHeaven on 12:38, 07/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UtilsSharedPresefenses {
    private static volatile UtilsSharedPresefenses INSTANCE;

    public static UtilsSharedPresefenses getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsSharedPresefenses.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsSharedPresefenses();
                }
            }
        }

        return INSTANCE;
    }

    public static Boolean getPermissionCap(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(Constants.SP_USER_PREFERENCE, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(Constants.SP_ENABLE_FUNCTIONS, false);
    }
}
