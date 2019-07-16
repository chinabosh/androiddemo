package com.bosh.module_mvp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bosh.module_mvp.R;
import com.china.bosh.mylibrary.annotation.BindEventBus;
import com.china.bosh.mylibrary.entity.DataEvent;
import com.china.bosh.mylibrary.utils.ToastUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author bosh
 * @date 2019-07-15
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    public Activity mActivity;
    private MaterialDialog progressDialog;
    /**
     * 设置布局文件
     * @return 布局文件id
     */
    @LayoutRes
    public abstract int getContentViewId();

    /**
     * dagger依赖注入
     */
    protected void initInject() {
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取数据
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        if(getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(mRootView);
        initInject();
        initView();
        initData();
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public <T> AutoDisposeConverter<T> bindLifecycle(){
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this));
    }

    public void showLoading() {
        showLoading("请稍候...");
    }
    public void showLoading(final String content) {
        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(s -> {
                    if (progressDialog == null || !progressDialog.isShowing()) {
                        progressDialog = new MaterialDialog.Builder(mActivity)
                                .content(content)
                                .contentGravity(GravityEnum.CENTER)
                                .cancelable(false)
                                .canceledOnTouchOutside(false)
                                .progressIndeterminateStyle(false)
                                .progress(true, 0)
                                .show();
                    }
                });
    }

    public void hideLoading() {
        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(s -> {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                });
    }

    public void toast(String msg) {
        ToastUtil.showShort(msg);
    }
    public void toast(int msgId){
        ToastUtil.showShort(msgId);
    }

    public void toastLong(String msg) {
        ToastUtil.showLong(msg);
    }
    public void toastLong(int msgId){
        ToastUtil.showLong(msgId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOnMainThread(DataEvent event) {
        onEvent(event);
    }

    public void onEvent(DataEvent event){
    }

    public void startActivity(Class targetClass){
        Intent modifyInfoIntent = new Intent(mActivity, targetClass);
        startActivity(modifyInfoIntent);
    }
}
