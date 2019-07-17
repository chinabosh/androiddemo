package com.bosh.module_mvp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bosh.module_mvp.R;
import com.bosh.module_mvp.R2;
import com.bosh.module_mvp.injector.components.ActivityComponent;
import com.bosh.module_mvp.ui.base.BaseActivity;
import com.bosh.module_mvp.ui.login.LoginActivity;

/**
 * @author bosh
 */
@Route(path = "/mvp/main")
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @Override
    protected int attachLayoutRes() {
        return R2.layout.mvp_activity_main;
    }

    @Override
    protected void initInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void goLogin() {
        startActivity(LoginActivity.class);
        finish();
    }
}
