package com.bosh.module_demo.ui.activity.recyclerview;


import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.bosh.module_demo.ui.adapter.MyAdapter;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author bosh
 * @date 2019/01/03
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R2.id.rv_main)
    RecyclerView mRvMain;

    private MyAdapter mAdapter;
    private List<String> mData;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_recycler_view;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        mData.add("001");
        mData.add("002");
        mData.add("003");
        mAdapter = new MyAdapter(mData);
        mRvMain.setLayoutManager(new LinearLayoutManager(this));
        mRvMain.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }
}
