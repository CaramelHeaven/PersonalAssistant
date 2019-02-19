package com.volgagas.personalassistant.utils.manager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:06, 18/02/2019.
 * <p>
 * Broadcast receiver for check state to update or not update current application
 */
public class BroadcastManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            Timber.d("INTENT GET ACTION: " + intent.getAction());
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (intent.getAction().equals(Constants.ACTION_NOT_UPDATE_APK)) {
                notificationManager.cancel(Constants.APP_NOTIFICATION_UPDATE_APP);
            } else if (intent.getAction().equals(Constants.ACTION_UPDATE_APK)) {
                RxBus.getInstance().passDataToCommonChannel(Constants.ACTION_FOR_DOWNLOAD_APK);
                notificationManager.cancel(Constants.APP_NOTIFICATION_UPDATE_APP);

                Toast.makeText(context, "UPDATING", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
