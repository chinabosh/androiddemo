package com.bosh.module_kotlin

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onCancel
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.callbacks.onPreShow
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.list.listItems
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.bosh.module_kotlin.widget.SpacesItemDecoration
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
        mData.add("回调")
        mData.add("列表")
        mData.add("列表单选")
        mData.add("列表单选，初始选中、不可选中")
        mData.add("列表多选，和单选类似")
    }

    override fun initView() {
        adapter = MaterialDialogAdapter(mData)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)
        adapter.onItemClickListener = this
//        rv_main.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        rv_main.addItemDecoration(SpacesItemDecoration(20, 1))
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (position) {
            0 -> MaterialDialog(this@MaterialDialogListActivity).show {
                title(text = "对话框")
                message(text = "这是一个对话框")
            }
            //另一种写法
//            0 -> {
//                val dialog = MaterialDialog(this)
//                        .title(R.string.text_dialog)
//                        .message(text = "这是一个对话框")
//                dialog.show()
//            }
            1 -> MaterialDialog(this).show {
                title(text = "对话框")
                message(text = "警告！")
                positiveButton(text = "知道了"){
                    //do positive action
                }
                negativeButton(text = "关闭")
            }
            2 -> MaterialDialog(this).show {
                title(text = "回调示例")
                message(text = "看打印信息")
                negativeButton(text = "取消")
                onPreShow {
                    Log.i(TAG, "显示前回调！")
                }
                onShow {
                    Log.i(TAG, "显示时回调！")
                }
                onDismiss {
                    Log.i(TAG, "消失时回调！")
                }
                onCancel {
                    Log.i(TAG, "取消时回调！")
                }
            }
            3 -> MaterialDialog(this).show {
                listItems(items = mData) { dialog, index, text ->
                    toast(text)
                }
            }
            4 -> MaterialDialog(this).show {
                listItemsSingleChoice(items = mData) { dialog, index, text ->
                    //有positiveButton，这个会等按下positionButton后回调，否则会在选中后就回调
                    toast("选中了" + text)
                }
                positiveButton(text = "确认")
            }
            5 -> MaterialDialog(this).show {
                listItemsSingleChoice(items = mData, initialSelection = 2, disabledIndices = intArrayOf(0,1))
            }
            //dialog.checkItem(index)
            //dialog.uncheckItem(index)
            //dialog.toggleItemChecked(index)
            //val checked : Boolean = dialog.isItemChecked(index)
            6 -> MaterialDialog(this).show {
                listItemsMultiChoice(items = mData, initialSelection = intArrayOf(2), disabledIndices = intArrayOf(0,1)) { dialog, indices, items ->
                    var str = "选中了"
                    for(item in items) {
                        str = str.plus(item).plus(",")
                    }
                    toast(str)
                }
                positiveButton(text = "确认")
            }
        }

    }
}
