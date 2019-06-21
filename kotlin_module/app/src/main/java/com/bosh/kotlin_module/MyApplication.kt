package com.bosh.kotlin_module

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author lzq
 * @date  2019/6/21
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}