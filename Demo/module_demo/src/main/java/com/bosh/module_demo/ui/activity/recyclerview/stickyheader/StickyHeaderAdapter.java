package com.bosh.module_demo.ui.activity.recyclerview.stickyheader;

import androidx.annotation.NonNull;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.jay.widget.StickyHeaders;

import java.util.List;

/**
 * @author lzq
 * @date 2019-08-14
 */
public class StickyHeaderAdapter extends BaseSectionQuickAdapter<StickyEntity, HeaderViewHolder> implements StickyHeaders {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public StickyHeaderAdapter(List<StickyEntity> data) {
        super(R.layout.demo_item_sticky, R.layout.demo_header_sticky, data);
    }

    @Override
    protected void convertHead(HeaderViewHolder helper, StickyEntity item) {
        helper.setText(R.id.tv_header, item.header);
    }

    @Override
    protected void convert(@NonNull HeaderViewHolder helper, StickyEntity item) {
        helper.setText(R.id.tv_item, item.t.getItem());
    }

    @Override
    public boolean isStickyHeader(int i) {
        return mData.get(i).isHeader;
    }
}
