package com.bosh.module_kotlin.ui

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bosh.module_kotlin.base.BaseActivity
import com.bosh.module_kotlin.databinding.KotlinActivityMainBinding
import com.bosh.module_kotlin.extension.inflate
import com.bosh.module_kotlin.ui.compose.ComposeTestActivity
import com.bosh.module_kotlin.ui.splash.SplashActivity
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

@Route(path = "/kotlin/main")
class MainActivity : BaseActivity() {

    private val binding : KotlinActivityMainBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun bindView() {
        binding.tvTest.text = "对话框例子列表"
        binding.tvTest.setOnClickListener {
            startActivity(Intent(this, MaterialDialogListActivity::class.java))
        }

        binding.tvShowDialog.setOnClickListener {
            showLoading()
            Observable.interval(5, TimeUnit.SECONDS)
                    .take(1)//限制发射次数
                    .autoDisposable(scopeProvider)
                    .subscribe {
                        hideLoading()
                    }
        }
        binding.tvSplash.setOnClickListener {
            startActivity(Intent(this, SplashActivity::class.java))
        }
        binding.tvCompose.setOnClickListener {
            startActivity(Intent(this, ComposeTestActivity::class.java))
        }
    }

    override fun initData() {

    }

    override fun initView() {
    }
}
