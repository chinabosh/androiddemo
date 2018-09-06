package com.china.bosh.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * @author lzq
 * @date 2018/9/6
 */

public abstract class BaseActivity extends com.china.bosh.mylibrary.ui.activity.BaseActivity{

    /**
     * get resource id
     * @return return resource id.
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);

        initView();
        initData();
    }
}
