package com.volgagas.personalassistant.utils.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.volgagas.personalassistant.utils.Constants;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by CaramelHeaven on 11:06, 19/02/2019.
 * <p>
 * Notification for uploading file. Show progress for user.
 */
public class FileUploadNotification {
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private int NOTIFICATION_ID = 111;
    private Context context;
    private int baseSize = 0;
    private static volatile FileUploadNotification fileUploadNotification;

    public static FileUploadNotification shared() {
        if (fileUploadNotification == null) {
            synchronized (FileUploadNotification.class) {
                if (fileUploadNotification == null) {
                    fileUploadNotification = new FileUploadNotification();
                }
            }
        }

        return fileUploadNotification;
    }

    public void createNotification(int size, String subText) {
        baseSize = size;

        builder = new NotificationCompat.Builder(context, "ChannelId33")
                .setSmallIcon(android.R.drawable.stat_sys_upload)
                .setContentTitle("")
                .setSubText(subText)
                .setProgress(baseSize, 0, false)
                .setAutoCancel(false);

        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "ChannelId33";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Скачивание новой версии приложения",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        Notification notification = builder.build();

        notificationManager.notify(Constants.APP_NOTIFICATION_UPDATE_APP, notification);
    }

    public void updateNotification(String percent) {
        builder.setContentText("Скачивание файла")
                .setContentTitle("")
                .setOngoing(true)
                .setContentInfo(percent + "%")
                .setProgress(baseSize, Integer.parseInt(percent), false);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
        if (Integer.parseInt(percent) == baseSize) {
            deleteNotification();
        }
    }

    public void deleteNotification() {
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
        builder = null;
        baseSize = 0;
        context = null;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
