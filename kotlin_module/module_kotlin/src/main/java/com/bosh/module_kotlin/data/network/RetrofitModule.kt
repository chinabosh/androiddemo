package com.bosh.module_kotlin.data.network

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author lzq
 * @date  2019-11-26
 */
const val RETROFIT_MODULE = "retrofit_module"
val retrofitModule = Kodein.Module(RETROFIT_MODULE) {
    bind<RetrofitUtil>() with singleton {
        RetrofitUtil()
    }
}