package com.bosh.module_mvp.injector;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.ui.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author lzq
 * @date 2019-07-15
 */
@Module
public class ActivityModule {

    private BaseActivity activity;
    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity provideActivity(){
        return activity;
    }
}
