package com.china.bosh.demo.ui.activity;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.china.bosh.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author chinabosh
 */
public class MainActivity extends BaseActivity {



    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_span})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_span:
                startActivity(SpannableActivity.class);
                break;
            default:
        }
    }

}