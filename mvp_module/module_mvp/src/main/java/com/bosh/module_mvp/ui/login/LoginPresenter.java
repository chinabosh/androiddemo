package com.bosh.module_mvp.ui.login;

import androidx.lifecycle.LifecycleOwner;

import com.bosh.module_mvp.network.NetPreFunction;
import com.bosh.module_mvp.network.ResponseData;
import com.bosh.module_mvp.ui.base.BasePresenter;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author bosh
 * @date 2019-07-10
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter{
    private LoginContract.View mLoginView;
    @Inject LoginContract.Model mLoginModel;
    @Inject LifecycleOwner mLifecycleOwner;

    @Inject
    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        //关联view的生命周期
        addLifecycleObserver();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void login() {
        String account = mLoginView.getAccount();
        String pwd = mLoginView.getPassword();
        mLoginModel.login(account, pwd)
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(mLifecycleOwner)))
                .subscribe();
    }
}
