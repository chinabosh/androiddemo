package com.bosh.module_mvp.injector;

import com.bosh.module_mvp.ui.base.BaseActivity;

import dagger.Component;
import dagger.Subcomponent;

/**
 * @author lzq
 * @date 2019-07-15
 */
@Component(modules = {BaseModule.class})
public interface BaseComponent {
    void inject(BaseActivity activity);
}
