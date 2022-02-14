package com.bosh.module_kotlin.ui.splash

import com.bosh.module_kotlin.base.BaseActivity
import com.bosh.module_kotlin.databinding.KotlinActivitySplashBinding
import com.bosh.module_kotlin.extension.inflate
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SplashActivity : BaseActivity() {

    private val kodein: Kodein = Kodein.lazy {
        import(splashModule)
    }

    private val binding : KotlinActivitySplashBinding by inflate()

    private val viewModel: SplashViewModel by kodein.instance()

    override fun bindView() {
        binding.tvCountTime.text = "5 跳过"
        viewModel.observeCountTime()
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(scopeProvider)
                .subscribe ({
                    val text = "$it 跳过"
                    binding.tvCountTime.text = text
                }, {

                }, {
                    goToMainActivity()
                })
        binding.tvCountTime.setOnClickListener {
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
