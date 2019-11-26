package com.bosh.kotlin_module

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.bosh.module_kotlin.data.db.DB_MODULE
import com.bosh.module_kotlin.data.db.DbUtil
import com.bosh.module_kotlin.data.db.dbModule
import com.bosh.module_kotlin.data.network.retrofitModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author lzq
 * @date  2019/6/21
 */
class MyApplication : Application() , KodeinAware{
    override val kodein: Kodein= Kodein.lazy{
        bind<MyApplication>() with singleton { this@MyApplication }
        import(dbModule)
        import(retrofitModule)
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

    }
}