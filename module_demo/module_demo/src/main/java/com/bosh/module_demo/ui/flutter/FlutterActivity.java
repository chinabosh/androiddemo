package com.bosh.module_demo.ui.flutter;

import android.view.View;
import android.widget.FrameLayout;

import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import butterknife.BindView;
import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

/**
 * @author bosh
 * @date 2019/4/30
 */
public class FlutterActivity extends BaseActivity {

    @BindView(R2.id.fl_content)
    FrameLayout mFlContent;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_flutter;
    }

    @Override
    protected void initView() {
        FlutterView view = Flutter.createView(this, getLifecycle(), "voice");
        mFlContent.addView(view);
        final FlutterView.FirstFrameListener listener = new FlutterView.FirstFrameListener() {
            @Override
            public void onFirstFrame() {
                mFlContent.setVisibility(View.VISIBLE);
            }
        };
        view.addFirstFrameListener(listener);

    }

    @Override
    protected void initData() {

    }
}
