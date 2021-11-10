package com.bosh.module_kotlin.ui

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onCancel
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.callbacks.onPreShow
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.checkbox.checkBoxPrompt
import com.afollestad.materialdialogs.checkbox.isCheckPromptChecked
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItems
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.bosh.module_kotlin.R
import com.bosh.module_kotlin.base.BaseActivity
import com.bosh.module_kotlin.databinding.KotlinActivityMaterialDialogListBinding
import com.bosh.module_kotlin.extension.inflate
import com.bosh.module_kotlin.widget.SpacesItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import java.lang.IllegalArgumentException

class MaterialDialogListActivity : BaseActivity(), OnItemClickListener {

    private var mData: ArrayList<String> = ArrayList()
    private val adapter: MaterialDialogAdapter by lazy {
        MaterialDialogAdapter(mData)
    }
    private val binding : KotlinActivityMaterialDialogListBinding by inflate()

    override fun bindView() {

    }

    override fun initData() {
        mData.add("纯对话框")
        mData.add("两个按钮对话框")
        mData.add("回调")
        mData.add("列表")
        mData.add("列表单选")
        mData.add("列表单选，初始选中、不可选中")
        mData.add("列表多选，和单选类似")
        mData.add("可勾选选项，如\"已同意协议\"")
        mData.add("自定义布局")
        mData.add("无法单一修改主题")
    }

    override fun initView() {
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener(this)
//        rv_main.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        binding.rvMain.addItemDecoration(SpacesItemDecoration(20, 1))
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
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
                positiveButton(text = "知道了") {
                    //do positive action
                }
                negativeButton(text = "关闭")
            }
            2 -> MaterialDialog(this).show {
                title(text = "回调示例")
                message(text = "看打印信息")
                negativeButton(text = "取消")
                onPreShow {
                    Log.i(tag, "显示前回调！")
                }
                onShow {
                    Log.i(tag, "显示时回调！")
                }
                onDismiss {
                    Log.i(tag, "消失时回调！")
                }
                onCancel {
                    Log.i(tag, "取消时回调！")
                }
            }
            3 -> MaterialDialog(this).show {
                listItems(items = mData) { _, _, text ->
                    toast(text)
                }
                positiveButton(text = "确认")
                noAutoDismiss()//点击选项不关闭对话框
            }
            4 -> MaterialDialog(this).show {
                listItemsSingleChoice(items = mData) { _, _, text ->
                    //有positiveButton，这个会等按下positionButton后回调，否则会在选中后就回调
                    toast("选中了" + text)
                }
                positiveButton(text = "确认")
            }
            5 -> MaterialDialog(this).show {
                listItemsSingleChoice(items = mData, initialSelection = 2, disabledIndices = intArrayOf(0, 1))
            }
            //dialog.checkItem(index)
            //dialog.uncheckItem(index)
            //dialog.toggleItemChecked(index)
            //val checked : Boolean = dialog.isItemChecked(index)
            6 -> MaterialDialog(this).show {
                listItemsMultiChoice(items = mData, initialSelection = intArrayOf(2), disabledIndices = intArrayOf(0, 1)) { _, _, items ->
                    var str = "选中了"
                    for (item in items) {
                        str = str.plus(item).plus(",")
                    }
                    toast(str)
                }
                positiveButton(text = "确认")
            }
            7 -> MaterialDialog(this).show {
                title(text = "开通会员")
                message(text = "开通会员，100元/月")
                checkBoxPrompt(text = "我已阅读并且同意用户协议") {

                }
                positiveButton(text = "确定") { dialog ->
                    val checked = dialog.isCheckPromptChecked()
                    if (checked) {
                        toast("用户阅读了协议并同意开通")
                    } else {
                        toast("用户同意开通，但是没有阅读协议")
                    }
                }
                negativeButton(text = "取消") {
                    val checked = it.isCheckPromptChecked()
                    if (checked) {
                        toast("用户阅读了协议，但取消了开通")
                    } else {
                        toast("用户没阅读协议就取消了开通")
                    }
                }
            }
            8 -> MaterialDialog(this).show {
                customView(R.layout.kotlin_dialog_custom_view, scrollable = false)
                positiveButton(text = "确认")
            }
            9 -> MaterialDialog(this).show {
                title(text = "标题")
                message(text = "内容")
                setTheme(R.style.kotlin_MaterialDialogCustomTheme)
                positiveButton(text = "确认")
                negativeButton(text = "取消")
            }
            else -> throw IllegalArgumentException("not type code yet!")
        }
    }
}
