package com.bosh.module_kotlin.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bosh.module_kotlin.R
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author lzq
 * @date  2019-10-28
 */
abstract class BaseActivity : AppCompatActivity() {

    val tag: String = javaClass.simpleName

    lateinit var loadingDialog: MaterialDialog

    var count = 0

    protected val scopeProvider : AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)
    }


    protected abstract fun bindView()

    protected abstract fun initData()

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(tag, "onCreate")
        bindView()
        initData()
        initView()
    }

    fun toast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        showLoading(getString(R.string.text_please_wait))
    }

    fun showLoading(msg: String) {
        count++
        val view = View.inflate(this, R.layout.kotlin_dialog_progress, null)
        val tv = view.findViewById<TextView>(R.id.tv_msg)
        tv.setText(msg)
        loadingDialog = MaterialDialog(this).customView(view = view)
        loadingDialog.show()
    }

    fun hideLoading() {
        //this::是必须的
        count--;
        if (count <= 0) {
            if (this::loadingDialog.isInitialized) {
                loadingDialog.dismiss()
            }
            count = 0;
        }
    }
}