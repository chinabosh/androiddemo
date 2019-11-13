package com.bosh.module_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.bosh.module_demo.utils.hook.HookHelper;
import com.bosh.module_demo.utils.hook.hookclick.IHookClickListener;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author bosh
 */
public class HookClickActivity extends BaseActivity {

    @BindView(R2.id.tv_common)
    TextView mTvCommon;
    @BindView(R2.id.tv_hook_common)
    TextView mTvHookCommon;
    @BindView(R2.id.tv_msg)
    TextView mTvMsg;

    private StringBuilder mMsg = new StringBuilder();

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_hook_click;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R2.id.tv_common, R2.id.tv_hook_common})
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.tv_common) {
            println("点击普通按钮");
        } else if (viewId == R.id.tv_hook_common) {
            println("hook一下普通按钮");
            try {
                HookHelper.hookOnClickListener(mTvCommon, new IHookClickListener() {
                    @Override
                    public void onBefore(View view) {
                        println("hook到了点击之前");
                    }

                    @Override
                    public void onAfter(View view) {
                        println("hook到了点击之后");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void println(String msg) {
        mMsg.append(msg).append("\n");
        mTvMsg.setText(mMsg.toString());
    }
}
