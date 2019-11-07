package com.bosh.module_kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author lzq
 * @date  2019-10-28
 */
abstract class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName


    protected abstract fun getLayoutRes() :  Int

    protected abstract fun initData()

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")
        setContentView(getLayoutRes())
        initData()
        initView()
    }

    fun toast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}