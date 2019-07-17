package com.china.bosh.demo;

import android.content.Context;
import android.os.Build;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.china.bosh.demo.BuildConfig;
import com.china.bosh.mylibrary.application.BaseApplication;
import com.china.bosh.mylibrary.db.DaoManager;
import com.china.bosh.mylibrary.utils.NotificationChannels;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lzq
 * @date 2018/9/6
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this);
        if(BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            closeAndroidPDialog();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initNotification();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * android 8.0以上通知
     */
    private void initNotification(){
        NotificationChannels.createAllNotificationChannels(this);
    }

    /**
     * 解决android P出现Detected problems with API compatibility 弹窗问题
     */
    private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
