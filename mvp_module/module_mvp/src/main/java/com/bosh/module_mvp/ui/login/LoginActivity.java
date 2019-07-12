package com.bosh.module_mvp.ui.login;

import android.widget.EditText;

import com.bosh.module_mvp.R;
import com.bosh.module_mvp.R2;
import com.bosh.module_mvp.injector.components.DaggerLoginComponent;
import com.bosh.module_mvp.injector.module.LoginModule;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author bosh
 */
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
    protected void initView() {

    }

    @Override
    protected void initData() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public String getAccount() {
        return mEtAccount.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPwd.getText().toString();
    }
}
