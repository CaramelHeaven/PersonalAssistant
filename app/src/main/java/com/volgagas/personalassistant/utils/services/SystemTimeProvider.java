package com.volgagas.personalassistant.utils.services;

/**
 * Created by CaramelHeaven on 17:22, 27/12/2018.
 */
public class SystemTimeProvider {
    private static SystemTimeProvider INSTANCE;

    public static SystemTimeProvider getInstance() {
        if (INSTANCE == null) {
            synchronized (SystemTimeProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SystemTimeProvider();
                }
            }
        }

        return INSTANCE;
    }
}
