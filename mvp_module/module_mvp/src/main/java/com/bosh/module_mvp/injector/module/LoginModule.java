package com.bosh.module_mvp.injector.module;

import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.injector.ActivityScope;
import com.bosh.module_mvp.injector.BaseComponent;
import com.bosh.module_mvp.injector.BaseModule;
import com.bosh.module_mvp.ui.login.LoginContract;
import com.bosh.module_mvp.ui.login.LoginModel;
import com.bosh.module_mvp.ui.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author lzq
 * @date 2019-07-10
 */
@Module()
public class LoginModule {
    private LoginContract.View loginView;
    private LifecycleOwner owner;
    public LoginModule(LoginContract.View view, LifecycleOwner owner) {
        loginView = view;
        this.owner = owner;
    }

    @Provides
    @ActivityScope
    public LoginContract.View provideView(){
        return loginView;
    }

    @Provides
    public LoginPresenter providePresenter(){
        return new LoginPresenter(loginView, owner);
    }
}
