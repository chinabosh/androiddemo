package com.bosh.module_kotlin.data.db

import android.app.Application
import com.bosh.module_kotlin.data.db.table.MyObjectBox
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.objectbox.BoxStore
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

/**
 * @author lzq
 * @date  2019-11-21
 */
const val DB_MODULE = "db_module"
val dbModule = Kodein.Module(DB_MODULE) {
    bind<BoxStore>() with scoped(AndroidLifecycleScope).singleton {
        MyObjectBox.builder().androidContext(instance()).build()
    }

    bind<DbUtil>() with singleton {
        DbUtil(instance())
    }
}