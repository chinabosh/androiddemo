package com.china.bosh.mylibrary.application;

import androidx.multidex.MultiDexApplication;

import com.china.bosh.mylibrary.utils.CrashHandler;
import com.china.bosh.mylibrary.utils.LogUtils;

/**
 * @author lzq
 * @date 2018/9/6
 */

public class BaseApplication  extends MultiDexApplication{

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initCrashHandler();
        instance = this;
    }

    /**
     * 初始化日志
     */
    private void initLog(){
        LogUtils.init(this);
    }

    /**
     * 初始化崩溃捕获
     */
    private void initCrashHandler(){
        CrashHandler.getInstance().init(this);
    }
}
