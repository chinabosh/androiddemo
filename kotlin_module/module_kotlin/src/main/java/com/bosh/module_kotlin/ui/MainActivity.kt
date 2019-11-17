package com.bosh.module_kotlin.ui

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bosh.module_kotlin.R
import com.bosh.module_kotlin.base.BaseActivity
import com.bosh.module_kotlin.databinding.KotlinActivityMainBinding
import io.reactivex.Observable
import kotlinx.android.synthetic.main.kotlin_activity_main.*
import java.util.concurrent.TimeUnit

@Route(path = "/kotlin/main")
class MainActivity : BaseActivity<KotlinActivityMainBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.kotlin_activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        tv_test.text = "对话框例子列表"
        tv_test.setOnClickListener {
            startActivity(Intent(this, MaterialDialogListActivity::class.java))
        }

        tv_show_dialog.setOnClickListener {
            showLoading()
            Observable.interval(5, TimeUnit.SECONDS)
                    .take(1)//限制发射次数
                    .subscribe {
                        hideLoading()
                    }
        }
    }

    override fun initView() {
    }
}
