package com.bosh.module_demo_test;

import android.content.Context;

import com.china.bosh.mylibrary.application.BaseApplication;

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
}
