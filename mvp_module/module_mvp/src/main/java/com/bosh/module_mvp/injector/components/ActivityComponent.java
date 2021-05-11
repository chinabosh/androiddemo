package com.bosh.module_mvp.injector.components;

import com.bosh.module_mvp.injector.module.ActivityModule;
import com.bosh.module_mvp.ui.base.BaseActivity;
import com.bosh.module_mvp.ui.login.LoginActivity;
import com.bosh.module_mvp.ui.main.MainActivity;

import dagger.Component;

/**
 * @author bosh
 * @date 2019-07-15
 */
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
//    <T extends BaseActivity> void inject(T activity);
    void inject(LoginActivity activity);
    void inject(MainActivity activity);
}
