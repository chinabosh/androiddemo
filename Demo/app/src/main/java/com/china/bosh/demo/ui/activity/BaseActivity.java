package com.china.bosh.demo.ui.activity;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.china.bosh.demo.application.MyApplication;
import com.china.bosh.mylibrary.utils.PermissionsManager;
import com.china.bosh.mylibrary.utils.PermissionsResultAction;

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

        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });

        initView();
        initData();
        setCustomDensity(this, MyApplication.instance);
    }

    /**
     * 来自今日头条技术团队 https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
     * 屏幕适配方案
     */
    private static float mNoncompatDensity;
    private static float mNoncompatScaledDensity;

    private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if(mNoncompatDensity == 0){
            mNoncompatDensity = appDisplayMetrics.density;
            mNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale> 0){
                        mNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (mNoncompatScaledDensity / mNoncompatDensity);
        final int targetDensityDpi = (int) (targetScaledDensity * 160);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
