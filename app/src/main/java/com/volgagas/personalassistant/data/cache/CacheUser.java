package com.volgagas.personalassistant.data.cache;

import com.volgagas.personalassistant.models.model.User;

import timber.log.Timber;

public class CacheUser {

    private static volatile CacheUser INSTANCE;
    private static volatile User user;

    private CacheUser() {
        if (INSTANCE == null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static CacheUser getInstance() {
        if (INSTANCE == null) {
            synchronized (CacheUser.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CacheUser();
                }
            }
        }
        return INSTANCE;
    }

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static void clear() {
        user = null;
    }
}
