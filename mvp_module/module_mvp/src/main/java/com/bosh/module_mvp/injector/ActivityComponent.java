package com.bosh.module_mvp.injector;

import com.bosh.module_mvp.ui.base.BaseActivity;
import com.bosh.module_mvp.ui.login.LoginActivity;

import dagger.Component;

/**
 * @author lzq
 * @date 2019-07-15
 */
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(LoginActivity activity);
}
