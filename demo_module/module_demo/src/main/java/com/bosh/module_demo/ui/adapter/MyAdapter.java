package com.bosh.module_demo.ui.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.bosh.module_demo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author lzq
 * @date 2019/1/3
 */

public class MyAdapter extends BaseQuickAdapter<String> {

    public MyAdapter(List<String> data) {
        super(R.layout.demo_item_recycler_view, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_hide, s);
        baseViewHolder.setOnClickListener(R.id.tv_hide, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout llDetail = baseViewHolder.getView(R.id.ll_detail);
                if(llDetail.getVisibility() == View.VISIBLE){
                    llDetail.setVisibility(View.GONE);
                }else {
                    llDetail.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
