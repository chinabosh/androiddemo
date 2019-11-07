package com.bosh.module_kotlin.widget

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

/**
 * 2017/6/14 0014.
 */
class SpacesItemDecoration(private val space: Int, private val spaceCount: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.bottom = space
        // 每行的第一个左边间隙设置为0
        if (parent.getChildLayoutPosition(view) % spaceCount == 0) {
            outRect.left = 0
        }
    }
}
