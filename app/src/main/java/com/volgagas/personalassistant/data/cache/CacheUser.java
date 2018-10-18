package com.volgagas.personalassistant.data.cache;

public class CacheUser {

    private static final CacheUser ourInstance = new CacheUser();

    static CacheUser getInstance() {
        return ourInstance;
    }

    private CacheUser() {
    }
}
