package com.bosh.module_mvp.ui.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import javax.inject.Inject;

/**
 * 处理生命周期问题
 * @author bosh
 * @date 2019-07-10
 */
public abstract class BasePresenter implements LifecycleObserver{

    @Inject
    LifecycleOwner mLifecycleOwner;

    public void addLifecycleObserver(){
        mLifecycleOwner.getLifecycle().addObserver(this);
    }

    public LifecycleOwner getLifecycleOwner(){
        return mLifecycleOwner;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public abstract void onDestroy();
}
