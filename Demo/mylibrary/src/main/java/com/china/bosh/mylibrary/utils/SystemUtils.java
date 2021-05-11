package com.china.bosh.mylibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.view.WindowManager;

/**
 * @author lzq
 * @date 2020/7/2
 */
public class SystemUtils {

    public static WindowManager getWindowManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getSystemService(WindowManager.class);
        } else {
            return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
    }

    public static ActivityManager getActivityManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getSystemService(ActivityManager.class);
        } else {
            return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
    }

    public static WifiManager getWifiManager(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getApplicationContext().getSystemService(WifiManager.class);
        } else {
            return (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
    }

    /**
     * 判断wifi是否开启
     * @param context context
     * @return true：开启，false：未开启
     */
    public static boolean isWifiOn(Context context) {
        WifiManager wifiManager = getWifiManager(context);
        return wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED;
    }
}
