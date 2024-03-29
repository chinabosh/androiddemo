package com.bosh.module_kotlin.ui

import com.bosh.module_kotlin.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author lzq
 * @date  2019-10-26
 */


class MaterialDialogAdapter constructor(data : MutableList<String>)
    : BaseQuickAdapter<String, BaseViewHolder>(R.layout.kotlin_item_material_dialog_list, data) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_type, item)
    }
}