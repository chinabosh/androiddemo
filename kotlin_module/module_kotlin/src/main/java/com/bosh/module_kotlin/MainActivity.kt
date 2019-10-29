package com.bosh.module_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.kotlin_activity_main.*

@Route(path = "/kotlin/main")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_main)
        tv_test.setText("对话框例子列表")
        tv_test.setOnClickListener {
            startActivity(Intent(this, MaterialDialogListActivity::class.java))
        }
    }

//    @OnClick(R2.id.tv_test, R2.id.tv_show_dialog)
//    fun onClick(view: View) {
//        var id = view.id;
//        if (id == R.id.tv_test) {
//            MaterialDialog(this).show {
//                title(text = "对话框")
//                message(text = "这是一个对话框")
//            }
//        }
//        if (id == R.id.tv_show_dialog) {
//            MaterialDialog(this).show {
//                title(text = "对话框")
//                message(text = "警告！")
//                positiveButton(text = "知道了")
//                negativeButton(text = "关闭")
//            }
//        }
//    }
}
