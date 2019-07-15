package com.bosh.module_mvp.ui.login;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bosh.module_mvp.R;
import com.bosh.module_mvp.R2;
import com.bosh.module_mvp.injector.components.DaggerLoginComponent;
import com.bosh.module_mvp.injector.module.LoginModule;
import com.bosh.module_mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author bosh
 */
@Route(path = "/mvp/login")
public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R2.id.et_account)
    EditText mEtAccount;
    @BindView(R2.id.et_pwd)
    EditText mEtPwd;

    @Inject LoginPresenter presenter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.mvp_activity_login;
    }

    @Override
    protected void initInject() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this, this))
                .build()
                .inject(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    public String getAccount() {
        return mEtAccount.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPwd.getText().toString();
    }

    @Override
    public void showLoginFail(String msg) {
        toast(msg);
    }

    @OnClick({R2.id.tv_login})
    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.tv_login) {
            presenter.login();
        }
    }
}
