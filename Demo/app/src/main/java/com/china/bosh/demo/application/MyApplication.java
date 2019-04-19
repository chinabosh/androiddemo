package com.china.bosh.demo.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.china.bosh.mylibrary.application.BaseApplication;
import com.china.bosh.mylibrary.db.DaoManager;

/**
 * @author lzq
 * @date 2018/9/6
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
