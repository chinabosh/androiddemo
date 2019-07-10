package com.bosh.module_mvp.ui.login;

import com.bosh.module_mvp.R;
import com.bosh.module_mvp.injector.components.DaggerLoginComponent;
import com.bosh.module_mvp.injector.module.LoginModule;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import javax.inject.Inject;

/**
 * @author bosh
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{

    @Inject LoginPresenter presenter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.mvp_activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this, this))
                .build()
                .inject(this);
    }
}
