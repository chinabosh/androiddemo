package com.bosh.module_kotlin

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author lzq
 * @date  2019-10-26
 */


class MaterialDialogAdapter constructor(data : MutableList<String>)
    : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_material_dialog_list, data) {

    override fun convert(helper: BaseViewHolder, item: String?) {
        helper.setText(R.id.tv_type, item)
    }
}