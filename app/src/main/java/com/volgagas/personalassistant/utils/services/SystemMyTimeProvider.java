package com.volgagas.personalassistant.utils.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by CaramelHeaven on 17:22, 27/12/2018.
 */
public class SystemMyTimeProvider {
    private static SystemMyTimeProvider INSTANCE;

    public static SystemMyTimeProvider getInstance() {
        if (INSTANCE == null) {
            synchronized (SystemMyTimeProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SystemMyTimeProvider();
                }
            }
        }

        return INSTANCE;
    }

    public static String folderNameTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
