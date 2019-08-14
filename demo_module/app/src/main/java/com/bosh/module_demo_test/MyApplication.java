package com.bosh.module_demo_test;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.china.bosh.mylibrary.application.BaseApplication;
import com.china.bosh.mylibrary.utils.NotificationChannels;

import androidx.multidex.MultiDex;


/**
 * @author lzq
 * @date 2019/6/5
 */
public class MyApplication extends BaseApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        NotificationChannels.createAllNotificationChannels(this);
    }
}
