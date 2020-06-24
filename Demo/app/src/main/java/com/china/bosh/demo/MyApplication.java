package com.china.bosh.demo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Build;

import androidx.annotation.RestrictTo;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.china.bosh.demo.BuildConfig;
import com.china.bosh.mylibrary.application.BaseApplication;
import com.china.bosh.mylibrary.db.DaoManager;
import com.china.bosh.mylibrary.utils.NotificationChannels;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzq
 * @date 2018/9/6
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
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
        initShortCuts(this);
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
    @SuppressWarnings({"unchecked", "PrivateApi"})
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

    private void initShortCuts(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            List<ShortcutInfo> list = new ArrayList<>();
            List<String> labels =  new ArrayList<>();
            labels.add("demo模块");
            labels.add("kotlin模块");
            labels.add("mvp模块");
            labels.add("mvvm模块");
            int count = 4;
            for (int i = 0; i < count; i++) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("intent_type", i);
                intent.setAction(Intent.ACTION_VIEW);
                ShortcutInfo info = new ShortcutInfo.Builder(context, "id" + i)
                        .setShortLabel(labels.get(i))
                        .setLongLabel("跳转模块：" + labels.get(i))
                        .setIntent(intent)
                        .build();
                list.add(info);
            }
            shortcutManager.addDynamicShortcuts(list);
        }

    }
}
