package com.bosh.module_demo.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

/**
 * @author bosh
 */
public class GaussActivity extends BaseActivity {

    @BindView(R2.id.iv_origin)
    ImageView mIvOrigin;
    @BindView(R2.id.iv_gauss)
    ImageView mIvGauss;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_gauss;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @OnClick({R2.id.tv_test})
    public void onClick(View view) {
        int id = view.getId();
        //模块中id不能用switch
        if(id == R.id.tv_test) {
            Blurry.with(this).capture(mIvOrigin).into(mIvGauss);
        }
    }
}
