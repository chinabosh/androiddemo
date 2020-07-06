package com.china.bosh.mylibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
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
}
