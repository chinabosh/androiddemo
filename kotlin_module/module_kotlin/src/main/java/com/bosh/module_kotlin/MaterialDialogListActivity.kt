package com.bosh.module_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_material_dialog_list.*

class MaterialDialogListActivity : BaseActivity() , BaseQuickAdapter.OnItemClickListener {

    var mData: ArrayList<String> = ArrayList()
    lateinit var adapter: MaterialDialogAdapter

    override fun getLayoutRes(): Int {
        return R.layout.activity_material_dialog_list
    }

    override fun initData() {
        mData.add("纯对话框")
        mData.add("两个按钮对话框")
    }

    override fun initView() {
        adapter = MaterialDialogAdapter(mData)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)
        adapter.onItemClickListener = this
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (position) {
            0 -> MaterialDialog(this@MaterialDialogListActivity).show {
                title(text = "对话框")
                message(text = "这是一个对话框")
            }
            1 -> MaterialDialog(this).show {
                title(text = "对话框")
                message(text = "警告！")
                positiveButton(text = "知道了")
                negativeButton(text = "关闭")
            }
        }

    }
}
