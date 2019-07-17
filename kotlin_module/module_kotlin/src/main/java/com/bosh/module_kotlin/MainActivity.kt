package com.bosh.module_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.kotlin_activity_main.*

@Route(path = "/kotlin/main")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_main)
        ButterKnife.bind(this@MainActivity)
        tv_test.setText("123")
    }
}
