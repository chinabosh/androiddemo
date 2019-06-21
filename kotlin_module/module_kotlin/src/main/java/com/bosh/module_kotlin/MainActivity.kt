package com.bosh.module_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.kotlin_activity_main.*

@Route(path = "/kotlin/test")
class MainActivity : AppCompatActivity() {

//    @BindView(R2.id.tv_test) lateinit var mTvTest : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_main)
        ButterKnife.bind(this@MainActivity)
//        mTvTest.setText("123")
        tv_test.setText("123")
    }
}
