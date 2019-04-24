package com.china.bosh.demo.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import java.util.Arrays;

/**
 * @author lzq
 * @date 2019/3/15
 */
@SuppressWarnings("all")
public class NotificationChannels {
    public final static String CRITICAL = "critical";
    public final static String IMPORTANCE = "importance";
    public final static String DEFAULT = "default";
    public final static String LOW = "low";
    public final static String MEDIA = "media";

    public static void createAllNotificationChannels(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(nm == null) {
            return;
        }

        NotificationChannel mediaChannel = new NotificationChannel(
                MEDIA,
                MEDIA,
                NotificationManager.IMPORTANCE_HIGH);
        mediaChannel.setSound(null,null);
        mediaChannel.setVibrationPattern(null);

        nm.createNotificationChannels(Arrays.asList(
                new NotificationChannel(
                        CRITICAL,
                        CRITICAL,
                        NotificationManager.IMPORTANCE_HIGH),
                new NotificationChannel(
                        IMPORTANCE,
                        IMPORTANCE,
                        NotificationManager.IMPORTANCE_DEFAULT),
                new NotificationChannel(
                        DEFAULT,
                        DEFAULT,
                        NotificationManager.IMPORTANCE_LOW),
                new NotificationChannel(
                        LOW,
                        LOW,
                        NotificationManager.IMPORTANCE_MIN),
                //custom notification channel
                mediaChannel
        ));
    }
}
