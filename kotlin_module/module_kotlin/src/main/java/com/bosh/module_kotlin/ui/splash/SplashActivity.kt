package com.bosh.module_kotlin.ui.splash

import android.util.Log
import com.bosh.module_kotlin.R
import com.bosh.module_kotlin.base.BaseActivity
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.kotlin_activity_splash.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SplashActivity : BaseActivity() {

    private val kodein: Kodein = Kodein.lazy {
        import(splashModule)
    }

    private val viewModel: SplashViewModel by kodein.instance()

    override fun getLayoutRes(): Int {
        return R.layout.kotlin_activity_splash
    }

    override fun bindView() {
        tv_count_time.text = "5 跳过"
        viewModel.observeCountTime()
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(scopeProvider)
                .subscribe ({
                    val text = "$it 跳过"
                    tv_count_time.text = text
                }, {

                }, {
                    goToMainActivity()
                })
        tv_count_time.setOnClickListener {
            goToMainActivity()
        }
    }

    override fun initData() {

    }

    override fun initView() {
        viewModel.startCountTime()
    }

    private fun goToMainActivity() {
        finish()
    }
}
