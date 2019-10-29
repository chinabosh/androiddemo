package com.bosh.module_kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author lzq
 * @date  2019-10-28
 */
abstract class BaseActivity : AppCompatActivity() {


    protected abstract fun getLayoutRes() :  Int

    protected abstract fun initData()

    protected abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutRes())
        initData()
        initView()
    }
}