package com.china.bosh.mylibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.china.bosh.mylibrary.annotation.BindEventBus;
import com.china.bosh.mylibrary.entity.DataEvent;
import com.china.bosh.mylibrary.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author lzq
 * @date 2018/7/4
 */

public abstract class BaseActivity extends FragmentActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBus.getDefault().unregister(this);
        }
    }

    public void startActivity(Class targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    public void toast(@StringRes int id){
        ToastUtil.showShort(id);
    }

    public void toast(String msg){
        ToastUtil.showShort(msg);
    }

    public void toastLong(@StringRes int id){
        ToastUtil.showLong(id);
    }

    public void toastLong(String msg){
        ToastUtil.showLong(msg);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onMainEvent(DataEvent event){
        onEvent(event);
    }

    public void onEvent(DataEvent event){}

}
