package com.bosh.module_mvp.injector.components;

import com.bosh.module_mvp.injector.ActivityScope;
import com.bosh.module_mvp.injector.BaseComponent;
import com.bosh.module_mvp.injector.module.LoginModule;
import com.bosh.module_mvp.ui.login.LoginActivity;

import dagger.Component;

/**
 * @author lzq
 * @date 2019-07-10
 */
@ActivityScope
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginView);
}
