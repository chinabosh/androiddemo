package com.bosh.module_mvp.injector;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.BuildConfig;
import com.bosh.module_mvp.interfaces.IPresenter;
import com.bosh.module_mvp.interfaces.IView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

/**
 * 处理生命周期问题
 * @author bosh
 * @date 2019-07-10
 */
public class BasePresenter<V extends IView> implements IPresenter {

    private LifecycleOwner owner;
    protected V mView;

    public BasePresenter(){

    }

    public void setLifecycleOwner(LifecycleOwner owner){
        this.owner = owner;
    }

    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        if(BuildConfig.DEBUG) {
            Log.i("BasePresenter", "onCreate");
        }
    }

    /**
     * 子类重新这个方法来做presenter的清理工作
     */
    @Override
    public void onDestroy() {
        if(BuildConfig.DEBUG) {
            Log.i("BasePresenter", "onDestroy");
        }
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if(BuildConfig.DEBUG) {
            Log.i("BasePresenter", "onLifecycleChanged, event:" + event.name());
        }
    }

    /**
     * rxJava 调用as(bindLifecycle）来关联view生命周期，防止泄漏
     * @param <T>
     * @return
     */
    protected <T> AutoDisposeConverter<T> bindLifecycle(){
        if(null == owner) {
            throw new NullPointerException("lifecycleOwner is null, check if super(owner) evaluate?");
        }
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner));
    }
}
