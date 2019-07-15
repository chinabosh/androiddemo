package com.bosh.module_mvp.interfaces;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author lzq
 * @date 2019-07-15
 */
public interface IPresenter extends LifecycleObserver {
    /**
     * 监听到view的onCreate时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    /**
     * 监听到view的onDestroy时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();

    /**
     * 监听到view生命周期改变时调用
     * @param owner
     * @param event 生命周期事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event);
}
