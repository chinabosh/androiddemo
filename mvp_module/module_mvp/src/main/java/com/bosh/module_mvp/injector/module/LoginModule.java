package com.bosh.module_mvp.injector.module;

import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.injector.ActivityScope;
import com.bosh.module_mvp.ui.login.LoginContract;
import com.bosh.module_mvp.ui.login.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author lzq
 * @date 2019-07-10
 */
@Module
public class LoginModule {
    private LoginContract.View loginView;
    private LifecycleOwner lifecycleOwner;
    public LoginModule(LoginContract.View view, LifecycleOwner lifecycleOwner) {
        loginView = view;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Provides
    @ActivityScope
    public LoginContract.View provideView(){
        return loginView;
    }

    @Provides
    public LifecycleOwner provideLifecycleOwner(){
        return lifecycleOwner;
    }

    @Provides
    public LoginContract.Model provideModel(){
        return new LoginModel();
    }
}
