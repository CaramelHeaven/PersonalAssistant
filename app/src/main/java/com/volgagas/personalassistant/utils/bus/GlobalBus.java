package com.volgagas.personalassistant.utils.bus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CaramelHeaven on 17:05, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class GlobalBus {
    private static EventBus eventBus;

    public static EventBus getEventBus() {
        if (eventBus != null) {
            eventBus = EventBus.getDefault();
        }
        return eventBus;
    }
}
