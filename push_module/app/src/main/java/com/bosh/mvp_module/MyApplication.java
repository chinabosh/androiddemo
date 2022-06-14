package com.bosh.mvp_module;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bosh.module_push.PushClient;

/**
 * @author bosh
 * @date 2019-07-10
 */
public class MyApplication extends Application {

    @Autowired(name = "push/client")
    public PushClient pushClient;

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
        ARouter.getInstance().inject(this);
        pushClient.initPush(this);
    }
}
