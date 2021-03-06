package com.bosh.module_mvp.ui.login;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bosh.module_mvp.ui.main.MainActivity;
import com.bosh.module_mvp.R;
import com.bosh.module_mvp.R2;
import com.bosh.module_mvp.injector.components.ActivityComponent;
import com.bosh.module_mvp.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.subjects.PublishSubject;

/**
 * @author bosh
 */
@Route(path = "/mvp/login")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R2.id.et_account)
    EditText mEtAccount;
    @BindView(R2.id.et_pwd)
    EditText mEtPwd;

    PublishSubject<String> publishSubject;

    @Override
    protected int attachLayoutRes() {
        return R.layout.mvp_activity_login;
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
        publishSubject = PublishSubject.create();
        //2秒内只触发一次
        publishSubject.throttleFirst(2, TimeUnit.SECONDS)
                .as(bindLifecycle())
                .subscribe(s -> {
                    mPresenter.login();
                }, Throwable::printStackTrace);
        mPresenter.initAccount();
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

    @Override
    public void setAccount(String account) {
        mEtAccount.setText(account);
    }

    @Override
    public void goMain() {
        startActivity(MainActivity.class);
        finish();
    }

    @OnClick({R2.id.tv_login})
    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.tv_login) {
            publishSubject.onNext("");
        }
    }
}
