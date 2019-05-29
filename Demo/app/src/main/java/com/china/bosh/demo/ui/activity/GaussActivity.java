package com.china.bosh.demo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.china.bosh.demo.R;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

/**
 * @author bosh
 */
public class GaussActivity extends BaseActivity {

    @BindView(R.id.iv_origin)
    ImageView mIvOrigin;
    @BindView(R.id.iv_gauss)
    ImageView mIvGauss;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_gauss;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_test:
                //高斯模糊
                Blurry.with(this).capture(mIvOrigin).into(mIvGauss);
                break;
            default:
        }
    }
}
