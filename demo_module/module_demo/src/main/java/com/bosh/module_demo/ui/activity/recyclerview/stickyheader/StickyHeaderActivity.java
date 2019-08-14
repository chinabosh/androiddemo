package com.bosh.module_demo.ui.activity.recyclerview.stickyheader;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;
import com.jay.widget.StickyHeadersLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author bosh
 */
public class StickyHeaderActivity extends BaseActivity {

    @BindView(R2.id.rv_main)
    RecyclerView mRvMain;

    StickyHeaderAdapter mAdapter;
    List<StickyEntity> mData;

    private String[] headers = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"
            , "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_sticky_header;
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        mAdapter = new StickyHeaderAdapter(mData);
        mRvMain.setLayoutManager(new StickyHeadersLinearLayoutManager<StickyHeaderAdapter>(this));
        mRvMain.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvMain.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        initListData();
    }

    private void initListData() {
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                mData.add(new StickyEntity(true, headers[i / 10]));
            }
            DataEntity dataEntity = new DataEntity();
            dataEntity.setItem(String.valueOf(i));
            StickyEntity entity = new StickyEntity(dataEntity);
            mData.add(entity);
        }
        mAdapter.notifyDataSetChanged();
    }
}
