package com.bosh.module_mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bosh.module_mvvm.base.BaseViewModel;

/**
 * @author lzq
 * @date 2019-08-14
 */
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BaseViewModel.class)) {
            //noinspection unchecked
            return (T) new BaseViewModel();
        }
        return super.create(modelClass);
    }
}
