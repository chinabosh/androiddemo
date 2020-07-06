package com.bosh.module_mvp.injector.module;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.ui.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author bosh
 * @date 2019-07-15
 */
@Module
@SuppressWarnings("rawtypes")
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
