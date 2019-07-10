package com.bosh.module_mvp.ui.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 处理生命周期问题
 * @author bosh
 * @date 2019-07-10
 */
public abstract class BasePresenter implements LifecycleObserver{

    private LifecycleOwner mLifecycleOwner;

    public void setLifecycleOwner(LifecycleOwner owner){
        mLifecycleOwner = owner;
        owner.getLifecycle().addObserver(this);
    }

    public LifecycleOwner getLifecycleOwner(){
        return mLifecycleOwner;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public abstract void onDestroy();
}
