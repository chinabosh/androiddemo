package com.bosh.module_kotlin.ui.splash

import com.bosh.module_kotlin.R
import com.bosh.module_kotlin.base.BaseActivity
import com.bosh.module_kotlin.databinding.KotlinActivitySplashBinding
import com.uber.autodispose.autoDisposable
import kotlinx.android.synthetic.main.kotlin_activity_splash.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SplashActivity : BaseActivity<KotlinActivitySplashBinding>() {

    val kodein: Kodein = Kodein.lazy {
        import(splashModule)
    }

    private val viewModel: SplashViewModel by kodein.instance()

    override fun getLayoutRes(): Int {
        return R.layout.kotlin_activity_splash
    }

    override fun initData() {
        viewModel.observeCountTime()
                .autoDisposable(scopeProvider)
                .subscribe {
                    val text = "$it 跳过"
                    tv_count_time.text = text
                }
    }

    override fun initView() {
        viewModel.startCountTime()
    }
}
