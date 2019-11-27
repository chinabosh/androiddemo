package com.bosh.module_mvvm.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author lzq
 * @date 2019-07-31
 */
public class BaseViewModel extends ViewModel {

    public BaseViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
