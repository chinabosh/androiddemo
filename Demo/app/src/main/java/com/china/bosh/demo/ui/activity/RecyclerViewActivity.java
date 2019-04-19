package com.china.bosh.demo.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.china.bosh.demo.R;
import com.china.bosh.demo.ui.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author bosh
 * @date 2019/01/03
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv_main)
    RecyclerView mRvMain;

    private MyAdapter mAdapter;
    private List<String> mData;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_recycler_view;
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
