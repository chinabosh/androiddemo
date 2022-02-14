package com.bosh.module_kotlin.ui.splash

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

/**
 * @author lzq
 * @date  2019-11-17
 */

const val SPLASH_MODULE = "splash_module"
val splashModule = Kodein.Module(SPLASH_MODULE) {
    bind<SplashViewModel>() with scoped<AppCompatActivity>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(context).get(SplashViewModel::class.java)
    }
}