package com.china.bosh.demo.ui.activity;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
