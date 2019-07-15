package com.bosh.module_mvp.injector;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import dagger.Module;
import dagger.Provides;

/**
 * @author lzq
 * @date 2019-07-15
 */
@Module
public class BaseModule {

    private LifecycleOwner owner;
    public BaseModule(LifecycleOwner owner) {
        this.owner = owner;
    }

    @Provides
    public BasePresenter providePresenter(){
        return new BasePresenter(owner);
    }
}
